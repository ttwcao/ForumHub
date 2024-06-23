package br.com.api.forumhub.domain.topico;

import br.com.api.forumhub.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="topico")
@Entity(name="Topico")

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Getter
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @Column(nullable=false)
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private Curso curso;

    public Topico(DadosCadastroTopico dados, Curso curso) {
        this.titulo = dados.titulo();;
        this.mensagem = dados.mensagem();
        this.data = LocalDateTime.now();
        this.status = dados.status();
        this.curso = curso;
    }
}
