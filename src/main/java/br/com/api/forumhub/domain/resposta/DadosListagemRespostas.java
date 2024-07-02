package br.com.api.forumhub.domain.resposta;

import br.com.api.forumhub.infra.utils.DateUtil;

import java.time.LocalDateTime;

public record DadosListagemRespostas(Long id, String mensagem, String data, String topico, String usuario) {
    public DadosListagemRespostas(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), DateUtil.formatarDataAmigavel(resposta.getData()), resposta.getTopico().getTitulo(), resposta.getUsuario().getNome());
    }
}
