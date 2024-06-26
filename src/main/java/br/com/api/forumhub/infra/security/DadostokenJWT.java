package br.com.api.forumhub.infra.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record DadostokenJWT(String token) {
}
