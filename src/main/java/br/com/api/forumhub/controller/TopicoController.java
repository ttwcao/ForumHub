package br.com.api.forumhub.controller;

import br.com.api.forumhub.domain.curso.Curso;
import br.com.api.forumhub.domain.curso.CursoRepository;
import br.com.api.forumhub.domain.topico.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController

@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroTopico dadosCadastroTopico, UriComponentsBuilder uriBuilder){

        //validação do token antes

        Topico topico = topicoService.cadastraTopico(dadosCadastroTopico);
        var uri = uriBuilder.path("topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalheamentoTopico(topico));
    }

}
