package br.com.api.forumhub.domain.resposta;

import br.com.api.forumhub.domain.topico.Topico;
import br.com.api.forumhub.domain.topico.TopicoRepository;
import br.com.api.forumhub.domain.usuario.Usuario;
import br.com.api.forumhub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //metodo para pegar o ID do usuário
    private Long GetUsuarioID(){
        //obter email do usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var email = auth.getName();

        //buscar o ID do usuário
        Long usuario = usuarioRepository.findIdByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não localizado!"));
        return usuario;
    }

    @Transactional
    public Resposta cadastraResposta(DadosCadastroResposta dadosCadastroResposta){
        Long topicoId = dadosCadastroResposta.topicoId();
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new IllegalArgumentException("Tópico não localizado"));

        Long usuarioId = dadosCadastroResposta.usuarioId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não localizado!"));

        Resposta resposta = new Resposta(dadosCadastroResposta, topico, usuario);
        return respostaRepository.save(resposta);
    }
}
