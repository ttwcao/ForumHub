package br.com.api.forumhub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCurso(
        @NotBlank
        @NotNull
        String nome,

        @NotNull
        Categoria categoria

) {
}
