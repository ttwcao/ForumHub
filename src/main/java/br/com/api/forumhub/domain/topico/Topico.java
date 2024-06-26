package br.com.api.forumhub.domain.topico;

import br.com.api.forumhub.domain.curso.Curso;
import br.com.api.forumhub.domain.resposta.Resposta;
import br.com.api.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Resposta> resposta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario usuario;

    public Topico(DadosCadastroTopico dados, Curso curso, Usuario usuario) {
        this.titulo = dados.titulo();;
        this.mensagem = dados.mensagem();
        this.data = LocalDateTime.now();
        this.status = Status.ABERTO;
        this.curso = curso;
        this.usuario = usuario;
    }

    public void atualizarInformacoes(DadosEditarTopico dados) {
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
        if(dados.cursoId() != null){
            this.curso = curso;
        }
    }
}
