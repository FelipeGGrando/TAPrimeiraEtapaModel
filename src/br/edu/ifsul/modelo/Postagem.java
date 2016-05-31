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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "postagem")
public class Postagem implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_postagem", sequenceName = "seq_postagem_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_postagem", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotBlank(message = "O título deve ser informado!")
    @Length(max = 50, message = "O título não deve ter mais que {max} caracteres...")
    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;
    @NotBlank(message = "O conteúdo deve ser informado!")
    @Length(max = 1000, message = "O conteúdo não deve ter mais que {max} caracteres...")
    @Column(name = "conteudo", length = 1000, nullable = false)
    private String conteudo;
    @NotNull(message = "A data de publicação deve ser informada...")
    @Column(name = "publicacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar publicacao;
    @Column(name = "publico", nullable = false)
    private Boolean publico;
    @NotNull(message = "A pessoa deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa", nullable = false, referencedColumnName = "id")
    private Pessoa pessoa;
    @ManyToOne
    @JoinColumn(name = "foto", referencedColumnName = "titulo", nullable = true)
    private Foto foto;
    @ManyToOne
    @JoinColumn(name = "video", nullable = true, referencedColumnName = "id")
    private Video video;
    @OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();

    public Postagem() {

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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Calendar getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Calendar publicacao) {
        this.publicacao = publicacao;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Postagem other = (Postagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.conteudo;
    }

}
