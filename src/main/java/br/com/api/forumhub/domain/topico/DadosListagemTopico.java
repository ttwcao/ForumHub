package br.com.api.forumhub.domain.topico;

import br.com.api.forumhub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosListagemTopico(Long id, String titulo, String mensagem, LocalDateTime data, Status status, String CursoNome, String UsuarioNome) {
    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData(), topico.getStatus(), topico.getCurso().getNome(), topico.getUsuario().getNome());
    }
}
