package br.com.api.forumhub.domain.topico;
import br.com.api.forumhub.domain.usuario.Usuario;
import br.com.api.forumhub.domain.usuario.UsuarioRepository;
import org.slf4j.Logger;
import br.com.api.forumhub.domain.curso.Curso;
import br.com.api.forumhub.domain.curso.CursoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    private static final Logger logger = LoggerFactory.getLogger(TopicoService.class);

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Topico cadastraTopico(DadosCadastroTopico dadosCadastroTopico){
        Long cursoID = dadosCadastroTopico.cursoId();
        logger.info("Curso ID Recebido: {}", cursoID);
        Curso curso = cursoRepository.findById(cursoID)
                .orElseThrow(() -> new IllegalArgumentException("Curso não localizado"));

        Usuario usuario = usuarioRepository.findById(dadosCadastroTopico.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não localizado"));

        Topico topico = new Topico(dadosCadastroTopico, curso, usuario);
        return topicoRepository.save(topico);
    }

}
