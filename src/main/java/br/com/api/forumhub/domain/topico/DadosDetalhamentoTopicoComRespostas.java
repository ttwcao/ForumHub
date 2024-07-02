package br.com.api.forumhub.domain.topico;

import br.com.api.forumhub.domain.resposta.DadosDetalhamentoResposta;
import br.com.api.forumhub.infra.utils.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoTopicoComRespostas(Long id, String titulo, String mensagem, String data, Status status, String cursoNome, String usuarioNome, List<DadosDetalhamentoResposta> respostas) {
    public DadosDetalhamentoTopicoComRespostas(Topico topico){
        this(
        topico.getId(),
        topico.getTitulo(),
        topico.getMensagem(), DateUtil.formatarDataAmigavel(topico.getData()),
        topico.getStatus(),
        topico.getCurso().getNome(),
        topico.getUsuario().getNome(),
        topico.getResposta().stream().map(DadosDetalhamentoResposta::new).toList()
        );
    }
}
