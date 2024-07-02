package br.com.api.forumhub.controller;

import br.com.api.forumhub.domain.resposta.*;
import br.com.api.forumhub.domain.topico.DadosCadastroTopico;
import br.com.api.forumhub.domain.topico.DadosDetalheamentoTopico;
import br.com.api.forumhub.domain.topico.Topico;
import br.com.api.forumhub.domain.topico.TopicoService;
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

@RequestMapping("resposta")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private RespostaRepository respostaRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroResposta dadosCadastroResposta, UriComponentsBuilder uriBuilder){
        Resposta resposta = respostaService.cadastraResposta(dadosCadastroResposta);
        var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoResposta> editarResposta(@PathVariable Long id, @RequestBody @Valid DadosEditarResposta dadosEditarResposta){
        DadosDetalhamentoResposta dadosAtualizados = respostaService.editarResposta(id, dadosEditarResposta);
        return ResponseEntity.ok(dadosAtualizados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemRespostas>> listar(@PageableDefault(size = 5) Pageable paginacao){
        var page = respostaRepository.findAll(paginacao).map(DadosListagemRespostas::new);
        return ResponseEntity.ok(page);
    }



//    @PostMapping
//    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroTopico dadosCadastroTopico, UriComponentsBuilder uriBuilder){
//            Topico topico = topicoService.cadastraTopico(dadosCadastroTopico);
//            var uri = uriBuilder.path("topico/{id}").buildAndExpand(topico.getId()).toUri();
//            return ResponseEntity.created(uri).body(new DadosDetalheamentoTopico(topico));
//    }

}
