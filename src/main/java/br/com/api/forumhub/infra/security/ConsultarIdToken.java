package br.com.api.forumhub.infra.security;

import br.com.api.forumhub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ConsultarIdToken {

    private final UsuarioRepository usuarioRepository;

    public ConsultarIdToken(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Long getIdUsuarioLogado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth.getName() == null){
            throw new EntityNotFoundException("Usuário não autenticado!");
        }

        String email = auth.getName();
        return usuarioRepository.findIdByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não localizado!"));
    }
}