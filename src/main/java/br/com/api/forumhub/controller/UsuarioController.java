package br.com.api.forumhub.controller;

import br.com.api.forumhub.domain.resposta.DadosCadastroResposta;
import br.com.api.forumhub.domain.resposta.DadosDetalhamentoResposta;
import br.com.api.forumhub.domain.resposta.Resposta;
import br.com.api.forumhub.domain.resposta.RespostaService;
import br.com.api.forumhub.domain.topico.DadosCadastroTopico;
import br.com.api.forumhub.domain.topico.DadosDetalheamentoTopico;
import br.com.api.forumhub.domain.topico.Topico;
import br.com.api.forumhub.domain.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController

@RequestMapping("resposta")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroResposta dadosCadastroResposta, UriComponentsBuilder uriBuilder){
        Resposta resposta = respostaService.cadastraResposta(dadosCadastroResposta);
        var uri = uriBuilder.path("resposta/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }
//    @PostMapping
//    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroTopico dadosCadastroTopico, UriComponentsBuilder uriBuilder){
//            Topico topico = topicoService.cadastraTopico(dadosCadastroTopico);
//            var uri = uriBuilder.path("topico/{id}").buildAndExpand(topico.getId()).toUri();
//            return ResponseEntity.created(uri).body(new DadosDetalheamentoTopico(topico));
//    }

}
