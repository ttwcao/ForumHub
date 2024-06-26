package br.com.api.forumhub.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResposta(
        @NotBlank
        @NotNull
        String mensagem,

        @NotNull
        Long topicoId,

        @NotNull
        Long usuarioId
) {
}
