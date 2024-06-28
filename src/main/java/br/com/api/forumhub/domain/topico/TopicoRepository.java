package br.com.api.forumhub.domain.topico;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE t.curso.id = :cursoId AND YEAR(t.data) = :ano AND t.status <> 'FECHADO'")
    List<Topico> findByCursoNomeAno(@Param("cursoId") Long cursoId, @Param("ano") int ano);

    Optional<Topico> findByTitulo(String titulo);
}