package br.com.api.forumhub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEditarTopico(
    @NotNull
    Long id,
    String titulo,
    String mensagem,
    @NotNull
    Long cursoId
){}
