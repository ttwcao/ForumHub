package br.com.api.forumhub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
        @NotBlank
        @NotNull
        String titulo,

        @NotBlank
        @NotNull
        String mensagem,

        @NotNull
        Long cursoId
)
{}
