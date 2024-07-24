package br.com.lyncas.contas.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contas", catalog = "contasdb")
public class Contas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dataVencimento;

    private Date dataPagamento;

    private BigDecimal valor;

    private String descricao;

    private Boolean situacao;

}
