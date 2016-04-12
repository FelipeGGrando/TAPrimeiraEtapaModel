/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Felipe
 */
@Embeddable
public class FotoID implements Serializable{
    @NotNull(message = "O número deve ser informado")
    @Column(name = "numero", nullable = false)
    private Integer numero;
    @NotNull(message = "A foto deve ser informada")
    @ManyToOne
    @JoinColumn(name = "foto", nullable = false, referencedColumnName = "id")
    private Foto foto;
    @NotNull(message = "A galeria deve ser informada")
    @ManyToOne
    @JoinColumn(name = "galeria", nullable = false, referencedColumnName = "id")
    private Galeria galeria;
    
    public FotoID() {
        
    }

    
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Galeria getGaleria() {
        return galeria;
    }

    public void setGaleria(Galeria galeria) {
        this.galeria = galeria;
    }
    
}
