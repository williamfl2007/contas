package br.com.lyncas.contas.service.impl;

import br.com.lyncas.contas.domain.dto.ContasDTO;
import br.com.lyncas.contas.domain.entity.Contas;
import br.com.lyncas.contas.repository.ContasRepository;
import br.com.lyncas.contas.service.GenericService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ContasServiceImpl implements GenericService<Contas> {

    private ContasRepository contasRepository;

    @Override
    public Optional<Contas> getId(Integer id) {
        return contasRepository.findById(id);
    }

    @Override
    public Page<Contas> getContas(Date dataVencimento, String descricao, Pageable pageable) {
        return contasRepository.findAll(dataVencimento,descricao,pageable);
    }

    @Override
    public Page<ContasDTO> getValorTotalPorPeriodo(Date dataVencimentoIncial, Date dataVencimentoFinal,
                                                   Pageable pageable){
        return contasRepository.findValorTotal(dataVencimentoIncial,dataVencimentoFinal,pageable);
    }

    @Override
    public Contas save(Contas entity) {
        return contasRepository.save(entity);
    }

    @Override
    public void remove(Contas entity) {
        contasRepository.delete(entity);
    }
}