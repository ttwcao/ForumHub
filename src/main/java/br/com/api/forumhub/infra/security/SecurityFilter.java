package br.com.api.forumhub.infra.security;

import br.com.api.forumhub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //recuperar o token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //System.out.println("Chamando filter");
        //recuperar o token dentro do cabeçalho
        var tokenJWT = recuperarToken(request);

        //sem isso aqui as requisições não passam no filtro .permitAll()
        //vai executar somente se tem cabeçalho
        if(tokenJWT != null) {
            //checagem de validar se o token esta correto
            var subject = tokenService.getSubject(tokenJWT);

            //dizer ao spring que o token esta válido
            //validar o login no banco pelo email de login
            var usuario = usuarioRepository.findByEmail(subject);

            //instaciar um novo objeto spring passando o login do usuario
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            //autenticação forçada para o spring
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //System.out.println("Logado na requisição");
        }


        //seguir o fluxo das requisições
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        //validar o token
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }
}