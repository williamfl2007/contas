package br.com.lyncas.contas.repository;

import br.com.lyncas.contas.domain.dto.ContasDTO;
import br.com.lyncas.contas.domain.entity.Contas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ContasRepository extends JpaRepository<Contas,Integer> {

    @Query(value = "select c from Contas c where dataVencimento=:dataVencimento and descricao=:descricao",
            countQuery = "select count(c) from Contas c where dataVencimento=:dataVencimento and descricao=:descricao")
    Page<Contas> findAll(Date dataVencimento, String descricao, Pageable pageable);

    @Query(value = "select new br.com.lyncas.contas.domain.dto.ContasDTO(c.dataVencimento,sum(c.valor)) " +
            " from Contas c where c.dataVencimento between :dataVencimentoInicial and :dataVencimentoFinal " +
            " group by c.dataVencimento",
            countQuery = "select count(*)" +
                    " from Contas c where c.dataVencimento between :dataVencimentoInicial and :dataVencimentoFinal ")
    Page<ContasDTO> findValorTotal(Date dataVencimentoInicial, Date dataVencimentoFinal, Pageable pageable);
}
