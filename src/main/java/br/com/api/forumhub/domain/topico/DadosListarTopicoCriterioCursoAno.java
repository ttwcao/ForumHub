package br.com.api.forumhub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosListarTopicoCriterioCursoAno(

        @NotBlank
        @NotNull
        LocalDateTime data,

        @NotNull
        Long cursoId
)
{}
