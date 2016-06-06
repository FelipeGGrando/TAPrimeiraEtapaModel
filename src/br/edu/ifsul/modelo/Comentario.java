/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "comentario")
public class Comentario implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_comentario", sequenceName = "seq_comentario_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_comentario", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotBlank(message = "O conteúdo deve ser informado!")
    @Length(max = 1000, message = "O conteúdo não deve ter mais que {max} caracteres...")
    @Column(name = "conteudo", length = 1000, nullable = false)
    private String conteudo;
    @NotNull(message = "A data de publicação deve ser informada...")
    @Column(name = "publicacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar publicacao;
    @NotNull(message = "A pessoa deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa", nullable = false, referencedColumnName = "id")
    private Pessoa pessoa;
    @ManyToOne
    @JoinColumn(name = "foto_id", referencedColumnName = "titulo", nullable = true)    
    private Foto foto;
    @ManyToOne
    @JoinColumn(name = "video_id", referencedColumnName = "id", nullable = true)    
    private Video video;
    @ManyToOne
    @JoinColumn(name = "postagem_id", referencedColumnName = "id", nullable = true)    
    private Postagem postagem;
    
    public Comentario() {
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Comentario other = (Comentario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.conteudo;
    }     

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
