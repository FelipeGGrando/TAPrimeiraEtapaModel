/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "video")
public class Video {

    @Id
    @SequenceGenerator(name = "seq_video", sequenceName = "seq_video_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_video", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotBlank(message = "O título deve ser informado!")
    @Length(max = 50, message = "O título não deve ter mais que {max} caracteres...")
    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;
    @Column(name = "publico", nullable = false)
    private Boolean publico;
    @NotNull(message = "A quantidade de visualizações deve ser informada!")
    @Column(name = "quantidade_visualizacoes", nullable = false)
    @Min(message = "A quantidade de visualizações não pode ser negativa!", value = 0)
    private Integer quantidadeVisualizacoes;
    @NotNull(message = "A data de publicação deve ser informada...")
    @Column(name = "publicacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar publicacao;
    @NotBlank(message = "O link deve ser informado!")
    @Length(max = 500, message = "O link não deve ter mais que {max} caracteres...")
    @Column(name = "link", length = 500, nullable = false)
    private String link;
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();

    public Video() {

    }
    
    public void adicionarComentario(Comentario obj) {
        this.comentarios.add(obj);
    }

    public void removerComentario(int index) {
        this.comentarios.remove(index);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }

    public Integer getQuantidadeVisualizacoes() {
        return quantidadeVisualizacoes;
    }

    public void setQuantidadeVisualizacoes(Integer quantidadeVisualizacoes) {
        this.quantidadeVisualizacoes = quantidadeVisualizacoes;
    }

    public Calendar getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Calendar publicacao) {
        this.publicacao = publicacao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Video other = (Video) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
