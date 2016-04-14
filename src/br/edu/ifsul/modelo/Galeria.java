/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "galeria")
public class Galeria implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_galeria", sequenceName = "seq_galeria_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_galeria", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotBlank(message = "O título deve ser informado!")
    @Length(max = 50, message = "O título não deve ter mais que {max} caracteres...")
    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;
    @NotBlank(message = "A descrição deve ser informada!")
    @Length(max = 500, message = "A descrição não deve ter mais que {max} caracteres...")
    @Column(name = "descricao", length = 500, nullable = false)
    private String descricao;
    @NotNull(message = "A pessoa deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa", nullable = false, referencedColumnName = "id")
    private Pessoa pessoa;
    @ManyToMany
    @JoinTable(name = "galeria_fotos", 
                    joinColumns = 
                    @JoinColumn(name = "galeria", referencedColumnName = "id"),
                    inverseJoinColumns = 
                    @JoinColumn(name = "foto", referencedColumnName = "numero"))
    private List<Foto> fotos = new ArrayList<>();
    
    public Galeria() {
        
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Galeria other = (Galeria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.titulo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
}
