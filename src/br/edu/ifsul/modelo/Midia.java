/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "midia")
//Classe abstrata que fornece herança
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Midia implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_midia", sequenceName = "seq_midia_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_midia", strategy = GenerationType.SEQUENCE)
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
    private Double quantidadeVisualizacoes;
    @NotNull(message = "A data de publicação deve ser informada...")
    @Column(name = "publicacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar publicacao;
    @NotNull(message = "A postagem deve ser informada")
    @ManyToOne
    @JoinColumn(name = "postagem", nullable = false, referencedColumnName = "id")
    private Postagem postagem;
      @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();
    
    public Midia() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Calendar publicacao) {
        this.publicacao = publicacao;
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

    public Double getQuantidadeVisualizacoes() {
        return quantidadeVisualizacoes;
    }

    public void setQuantidadeVisualizacoes(Double quantidadeVisualizacoes) {
        this.quantidadeVisualizacoes = quantidadeVisualizacoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Midia other = (Midia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.titulo;
    }

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    
    
}
