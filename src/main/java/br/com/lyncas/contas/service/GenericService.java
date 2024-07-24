package br.com.lyncas.contas.service;


import br.com.lyncas.contas.domain.dto.ContasDTO;
import br.com.lyncas.contas.domain.entity.Contas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.Optional;

public abstract interface GenericService<T> {


    public Page<T> getContas(Date dataVencimento, String descricao,Pageable pageable);

    public Page<ContasDTO> getValorTotalPorPeriodo(Date dataVencimentoIncial, Date dataVencimentoFinal,
                                                   Pageable pageable);

    public T save(T entity);

    public void remove(T entity);

    public Optional<T> getId(Integer id);

}
