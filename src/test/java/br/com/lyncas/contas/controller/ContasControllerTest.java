package br.com.lyncas.contas.controller;

import br.com.lyncas.contas.domain.dto.ContasDTO;
import br.com.lyncas.contas.domain.entity.Contas;
import br.com.lyncas.contas.exception.ResourceNotFoundException;
import br.com.lyncas.contas.service.impl.ContasServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ContasControllerTest {

    @Mock
    private ContasServiceImpl service;

    @InjectMocks
    private ContasController controller;

    private Contas conta;
    private ContasDTO contaDTO;
    private Page<Contas> contasPage;
    private Page<ContasDTO> contasDTOPage;

    @BeforeEach
    void setUp() {
        conta = new Contas();
        contaDTO = new ContasDTO();
        contasPage = new PageImpl<>(Arrays.asList(conta));
        contasDTOPage = new PageImpl<>(Arrays.asList(contaDTO));
    }

    @Test
    void testGetAll() {
        Date dataVencimento = new Date();
        String descricao = "descricao";
        Pageable pageable = PageRequest.of(0, 10);

        when(service.getContas(dataVencimento, descricao, pageable)).thenReturn(contasPage);

        ResponseEntity<Page<Contas>> response = controller.getAll(dataVencimento, descricao, pageable);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(contasPage);
    }

    @Test
    void testGetValorTotalPorPeriodo() {
        Date dataVencimentoInicio = new Date();
        Date dataVencimentoFinal = new Date();
        Pageable pageable = PageRequest.of(0, 10);

        when(service.getValorTotalPorPeriodo(dataVencimentoInicio, dataVencimentoFinal, pageable)).thenReturn(contasDTOPage);

        ResponseEntity<Page<ContasDTO>> response = controller.getValorTotalPorPeriodo(dataVencimentoInicio, dataVencimentoFinal, pageable);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(contasDTOPage);
    }

    @Test
    void testGetObject() throws ResourceNotFoundException {
        int id = 1;
        when(service.getId(id)).thenReturn(Optional.of(conta));

        ResponseEntity<Contas> response = controller.getObject(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(conta);
    }

    @Test
    void testCreate() {
        when(service.save(conta)).thenReturn(conta);

        ResponseEntity<Contas> response = controller.create(conta);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void testUpdate() throws ResourceNotFoundException {
        int id = 1;
        when(service.getId(id)).thenReturn(Optional.of(conta));
        when(service.save(conta)).thenReturn(conta);

        ResponseEntity<Contas> response = controller.update(id, conta);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo(conta);
    }

    @Test
    void testDelete() throws ResourceNotFoundException {
        int id = 1;
        when(service.getId(id)).thenReturn(Optional.of(conta));

        ResponseEntity<Contas> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service, times(1)).remove(conta);
    }
}