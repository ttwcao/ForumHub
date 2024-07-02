package br.com.api.forumhub.domain.resposta;

import br.com.api.forumhub.domain.topico.Topico;
import br.com.api.forumhub.domain.topico.TopicoRepository;
import br.com.api.forumhub.domain.usuario.Usuario;
import br.com.api.forumhub.domain.usuario.UsuarioRepository;
import br.com.api.forumhub.infra.security.ConsultarIdToken;
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

    @Autowired
    private ConsultarIdToken consultarIdToken;

    @Transactional
    public Resposta cadastraResposta(DadosCadastroResposta dadosCadastroResposta){
        //obter id do usuário
        Long usuarioId = consultarIdToken.getIdUsuarioLogado();

        Long topicoId = dadosCadastroResposta.topicoId();
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new IllegalArgumentException("Tópico não localizado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não localizado!"));

        Resposta resposta = new Resposta(dadosCadastroResposta, topico, usuario);
        return respostaRepository.save(resposta);
    }

    @Transactional
    public DadosDetalhamentoResposta editarResposta(Long id, DadosEditarResposta dadosEditarResposta) {
        //obter usuário logado
        Long usuarioId = consultarIdToken.getIdUsuarioLogado();

        //obter resposta
        var resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta não localizada!"));

        if(dadosEditarResposta.topicoId() != null){
            var topico = topicoRepository.findById(dadosEditarResposta.topicoId());
            if(topico.isEmpty()){
                throw new EntityNotFoundException("Tópico relacionado a resposta não localizado!");
            }
        }

        if(!resposta.getUsuario().getId().equals(usuarioId)){
            throw new SecurityException("Você não tem permissão para editar este tópico!");
        } else{
            resposta.atualizarInformacoes(dadosEditarResposta);
            return new DadosDetalhamentoResposta(resposta);
        }
    }
}
