package br.com.alurafood.domain.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosListagemPagamento(Long id, BigDecimal valor, String nome, String numero, LocalDateTime expiracao, String codigo, Status status, Long pedidoId, Long formaDePagamentoId) {

    public DadosListagemPagamento(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getValor(), pagamento.getNome(), pagamento.getNumero(), pagamento.getExpiracao(), pagamento.getCodigo(), pagamento.getStatus(), pagamento.getPedidoId(), pagamento.getFormaDePagamentoId());
    }

}
