package br.com.api.forumhub.domain.curso;

import br.com.api.forumhub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="curso")
@Entity(name="Curso")

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Topico> topico;

    public Curso(DadosCadastroCurso dados){
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
}
