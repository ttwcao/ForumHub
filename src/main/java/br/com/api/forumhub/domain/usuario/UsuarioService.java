package br.com.api.forumhub.domain.usuario;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario cadastrarUsuario(DadosCadastroUsuario dadosCadastroUsuario){
        Usuario usuario = new Usuario(dadosCadastroUsuario);
        return usuarioRepository.save(usuario);
    }
}
