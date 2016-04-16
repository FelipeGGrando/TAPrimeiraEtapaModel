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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "foto")
public class Foto implements Serializable {

    @EmbeddedId
    private FotoID fotoId;
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
    @NotBlank(message = "O endereço deve ser informado!")
    @Length(max = 500, message = "O endereço não deve ter mais que {max} caracteres...")
    @Column(name = "endereco", length = 500, nullable = false)
    private String endereco;

    @ManyToMany
    @JoinTable(name = "galeria_fotos",
            joinColumns
            = {
                @JoinColumn(name = "foto_numero", referencedColumnName = "numero", nullable = true),
                @JoinColumn(name = "foto_galeria", referencedColumnName = "galeria", nullable = true)},
            inverseJoinColumns = @JoinColumn(name = "galeria", referencedColumnName = "id")
    )
    private List<Galeria> galerias = new ArrayList<>();

    @OneToMany(mappedBy = "foto", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();

    public Foto() {

    }

    public FotoID getFotoId() {
        return fotoId;
    }

    public void setFotoId(FotoID fotoId) {
        this.fotoId = fotoId;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Galeria> getGalerias() {
        return galerias;
    }

    public void setGalerias(List<Galeria> galerias) {
        this.galerias = galerias;
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
        hash = 79 * hash + Objects.hashCode(this.fotoId);
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
        final Foto other = (Foto) obj;
        if (!Objects.equals(this.fotoId, other.fotoId)) {
            return false;
        }
        return true;
    }

}
