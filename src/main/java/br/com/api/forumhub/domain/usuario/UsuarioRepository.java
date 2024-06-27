package br.com.api.forumhub.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);

    @Query("SELECT u.id FROM Usuario u WHERE u.email = :email")
    Optional<Long> findIdByEmail(@Param("email") String email);

    Page<Usuario> findAll(Pageable paginacao);
}
