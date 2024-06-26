package br.com.api.forumhub.controller;

import br.com.api.forumhub.domain.resposta.DadosCadastroResposta;
import br.com.api.forumhub.domain.resposta.DadosDetalhamentoResposta;
import br.com.api.forumhub.domain.resposta.Resposta;
import br.com.api.forumhub.domain.resposta.RespostaService;
import br.com.api.forumhub.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController

@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario, UriComponentsBuilder uriBuilder){
        Usuario usuario = usuarioService.cadastrarUsuario(dadosCadastroUsuario);
        var uri = uriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuarios>> listar(@PageableDefault(size = 5, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable paginacao){
        var page = usuarioRepository.findAll(paginacao).map(DadosListagemUsuarios::new);
        return ResponseEntity.ok(page);
    }

}
