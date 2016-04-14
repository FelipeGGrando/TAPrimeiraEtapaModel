/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "foto")
public class Foto extends Midia implements Serializable{
    
    @EmbeddedId
    private FotoID fotoId;
    @NotBlank(message = "A legenda deve ser informada!")
    @Length(max = 50, message = "A legenda não deve ter mais que {max} caracteres...")
    @Column(name = "legenda", length = 50, nullable = false)
    private String legenda;
    @NotBlank(message = "O endereço deve ser informado!")
    @Length(max = 500, message = "O endereço não deve ter mais que {max} caracteres...")
    @Column(name = "legenda", length = 500, nullable = false)
    private String endereco;
    @ManyToMany
    @JoinTable(name = "galeria_fotos", 
                    joinColumns = 
                    @JoinColumn(name = "foto", referencedColumnName = "numero"),
                    inverseJoinColumns = 
                    @JoinColumn(name = "galeria", referencedColumnName = "id"))
    private List<Galeria> galerias = new ArrayList<>();
    
    
    public Foto() {
        
    }
    public FotoID getFotoId() {
        return fotoId;
    }

    public void setFotoId(FotoID fotoId) {
        this.fotoId = fotoId;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
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
    
}
