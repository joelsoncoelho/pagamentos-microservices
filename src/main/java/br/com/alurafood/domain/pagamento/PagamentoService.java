package br.com.alurafood.domain.pagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento criarPagamento(DadosCadastroPagamento dados) {
        var pagamento = new Pagamento(dados);
        pagamento.mudarStatus(Status.CRIADO);
        pagamentoRepository.save(pagamento);
        return pagamento;

    }

}
