package br.com.api.forumhub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank
        @NotNull
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @NotNull
        String senha
) {
}
