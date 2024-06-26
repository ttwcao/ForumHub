package br.com.api.forumhub.domain.resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(Long id, String mensagem, LocalDateTime data, String topico, String usuario) {
    public DadosDetalhamentoResposta(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getData(), resposta.getTopico().getTitulo(), resposta.getUsuario().getNome());
    }
}
