package br.com.alurafood.domain.pagamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "pagamentos")
@Entity(name = "Pagamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    private String nome;

    private String numero;

    private LocalDateTime expiracao;

    private String codigo;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long pedidoId;

    private Long formaDePagamentoId;

    public Pagamento(DadosCadastroPagamento dados){
        this.valor = dados.valor();
        this.nome = dados.nome();
        this.numero = dados.numero();
        this.expiracao = dados.expiracao();
        this.codigo = dados.codigo();
        this.status = dados.status();
        this.pedidoId = dados.pedidoId();
        this.formaDePagamentoId = dados.formaDePagamentoId();
    }

    public void mudarStatus(Status status){
        this.status = status;
    }

    public void atualizaInformacoes(DadosAtualizacaoPagamento dados) {
        if(dados.valor() != null){
            this.valor = dados.valor();
        }
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.numero() != null){
            this.numero = dados.numero();
        }
        if(dados.expiracao() != null){
            this.expiracao = dados.expiracao();
        }
        if(dados.codigo() != null){
            this.codigo = dados.codigo();
        }
        if(dados.status() != null){
            mudarStatus(dados.status());
        }
    }

}
