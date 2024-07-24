package br.com.lyncas.contas.service.impl;

import br.com.lyncas.contas.domain.dto.ContasDTO;
import br.com.lyncas.contas.domain.entity.Contas;
import br.com.lyncas.contas.repository.ContasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContasServiceImplTest {

    @InjectMocks
    private ContasServiceImpl contasService;

    @Mock
    private ContasRepository contasRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        Integer id = 1;
        Contas conta = new Contas();
        when(contasRepository.findById(id)).thenReturn(Optional.of(conta));

        Optional<Contas> result = contasService.getId(id);

        assertTrue(result.isPresent());
        assertEquals(conta, result.get());
    }

    @Test
    void testGetContas() {
        Date dataVencimento = new Date();
        String descricao = "Descricao";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Contas> page = new PageImpl<>(List.of(new Contas()));
        when(contasRepository.findAll(dataVencimento, descricao, pageable)).thenReturn(page);

        Page<Contas> result = contasService.getContas(dataVencimento, descricao, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetValorTotalPorPeriodo() {
        Date dataVencimentoIncial = new Date();
        Date dataVencimentoFinal = new Date();
        Pageable pageable = PageRequest.of(0, 10);
        Page<ContasDTO> page = new PageImpl<>(List.of(new ContasDTO()));
        when(contasRepository.findValorTotal(dataVencimentoIncial, dataVencimentoFinal, pageable)).thenReturn(page);

        Page<ContasDTO> result = contasService.getValorTotalPorPeriodo(dataVencimentoIncial, dataVencimentoFinal, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testSave() {
        Contas entity = new Contas();
        when(contasRepository.save(entity)).thenReturn(entity);

        Contas result = contasService.save(entity);

        assertNotNull(result);
        assertEquals(entity, result);
    }

    @Test
    void testRemove() {
        Contas entity = new Contas();
        doNothing().when(contasRepository).delete(entity);

        contasService.remove(entity);

        verify(contasRepository, times(1)).delete(entity);
    }

}