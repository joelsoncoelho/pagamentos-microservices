package br.com.alurafood.controller;

import br.com.alurafood.domain.pagamento.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPagamento dados, UriComponentsBuilder uriComponentsBuilder){

        var pagamento = pagamentoService.criarPagamento(dados);

        var uri = uriComponentsBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPagamento(pagamento));
    }
 /*
    @GetMapping
    public List<DadosListagemPagamento> listar() {
        return pagamentoRepository.findAll().stream().map(DadosListagemPagamento::new).toList();
    }
  */
    @GetMapping
    public ResponseEntity<Page<DadosListagemPagamento>> listarPaginada(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable){
       var page = pagamentoRepository.findAll(pageable).map(DadosListagemPagamento::new);
       return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar (@PathVariable Long id){
       var pagamento = pagamentoRepository.getReferenceById(id);
       return ResponseEntity.ok(new DadosDetalhamentoPagamento(pagamento));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid DadosAtualizacaoPagamento dados){

        var pagamento = pagamentoRepository.getReferenceById(dados.id());
        pagamento.atualizaInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPagamento(pagamento));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id){
        pagamentoRepository.deleteById(id);
         return ResponseEntity.noContent().build();
    }

}
