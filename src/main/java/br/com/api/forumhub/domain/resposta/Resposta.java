package br.com.api.forumhub.domain.resposta;

import br.com.api.forumhub.domain.topico.Topico;
import br.com.api.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="resposta")
@Entity(name = "Resposta")

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter

public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    private LocalDateTime data;
    private Boolean solucao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario usuario;

    public Resposta(DadosCadastroResposta dados, Topico topico, Usuario usuario){
        this.mensagem = dados.mensagem();
        this.data = LocalDateTime.now();
        this.solucao = false;
        this.topico = topico;
        this.usuario = usuario;
    }

    public void atualizarInformacoes(DadosEditarResposta dados) {
        if(dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
    }
}
