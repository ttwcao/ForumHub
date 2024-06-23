package br.com.api.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalheamentoTopico(Long id, String titulo, String mensagem, LocalDateTime data, Status status, String CursoNome) {
    public DadosDetalheamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData(), topico.getStatus(), topico.getCurso().getNome());
    }
}
