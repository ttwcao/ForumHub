package br.com.api.forumhub.domain.topico;

import br.com.api.forumhub.infra.utils.DateUtil;

import java.time.LocalDateTime;

public record DadosDetalheamentoTopico(Long id, String titulo, String mensagem, String data, Status status, String CursoNome, String UsuarioNome) {
    public DadosDetalheamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), DateUtil.formatarDataAmigavel(topico.getData()), topico.getStatus(), topico.getCurso().getNome(), topico.getUsuario().getNome());
    }
}
