package br.com.api.forumhub.controller;

import br.com.api.forumhub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController

@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private  TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroTopico dadosCadastroTopico, UriComponentsBuilder uriBuilder){

        Topico topico = topicoService.cadastraTopico(dadosCadastroTopico);
        var uri = uriBuilder.path("topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalheamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 5) Pageable paginacao){
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    //filtro para consulta
    @PostMapping("/filtro")
    public ResponseEntity<List<DadosListagemTopico>> listaFiltro(@RequestBody @Valid DadosFiltroTopico dados){
        List<DadosListagemTopico> dadosListagemTopicos = topicoService.listaFiltro(dados.cursoId(), dados.ano());
        if(dadosListagemTopicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.ok(dadosListagemTopicos);
        }
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalhar(@PathVariable Long id){
        var consultaTopico = topicoRepository.findById(id);
        if(consultaTopico.isPresent()){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalheamentoTopico(topico));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico não localizado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalheamentoTopico> editarTopico(@PathVariable Long id, @RequestBody @Valid DadosEditarTopico dados){
        DadosDetalheamentoTopico dadosAtualizados = topicoService.editarTopico(id, dados);
        return ResponseEntity.ok(dadosAtualizados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        topico.desabilitar();
        return ResponseEntity.noContent().build();

    }


}
