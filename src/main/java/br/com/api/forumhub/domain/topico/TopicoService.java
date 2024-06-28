package br.com.api.forumhub.domain.topico;
import br.com.api.forumhub.domain.usuario.Usuario;
import br.com.api.forumhub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import br.com.api.forumhub.domain.curso.Curso;
import br.com.api.forumhub.domain.curso.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import br.com.api.forumhub.infra.security.ConsultarIdToken;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConsultarIdToken consultarIdToken;


    @Transactional
    public Topico cadastraTopico(DadosCadastroTopico dadosCadastroTopico){

        //verificar o curso informado
        Long cursoID = dadosCadastroTopico.cursoId();
        Curso curso = cursoRepository.findById(cursoID)
                .orElseThrow(() -> new EntityNotFoundException("Curso não localizado"));

        //obter id do usuário
        Long usuarioId = consultarIdToken.getIdUsuarioLogado();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não localizado"));

        //validar se já existe tópico com mesmo nome
        if(topicoRepository.findByTitulo(dadosCadastroTopico.titulo()).isPresent()){
                throw new RuntimeException("Já existe um tópico com este nome!");
        }

        //executar cadastro
        Topico topico = new Topico(dadosCadastroTopico, curso, usuario);
        return topicoRepository.save(topico);
    }

    @Transactional
    public List<DadosListagemTopico> listaFiltro(Long cursoId, int ano){
        List<Topico> topicos = topicoRepository.findByCursoNomeAno(cursoId, ano);
        if(topicos.isEmpty()){
            return Collections.emptyList();
        }else {
            return topicos.stream()
                    .map(DadosListagemTopico::new)
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    public DadosDetalheamentoTopico editarTopico(Long topicoId, DadosEditarTopico dadosEditarTopico){

        //obter id do usuário
        Long usuarioId = consultarIdToken.getIdUsuarioLogado();


        //obter o tópico
        var topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não localizado!"));

        if(dadosEditarTopico.cursoId() != null){
            var curso = cursoRepository.findById(dadosEditarTopico.cursoId());
            if(curso.isEmpty()){
                throw new EntityNotFoundException("Curso não localizado");
            }
        }

        if(!topico.getUsuario().getId().equals(usuarioId)){
            throw new SecurityException("Você não tem permissão para editar este tópico!");
        } else {
            topico.atualizarInformacoes(dadosEditarTopico);
            return new DadosDetalheamentoTopico(topico);
        }

    }



}
