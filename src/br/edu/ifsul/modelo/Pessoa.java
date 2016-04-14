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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "pessoa")
public class Pessoa extends Usuario implements Serializable{
    @NotBlank(message = "O nome deve ser informado!")
    @Length(max = 50, message = "O nome não deve ter mais que {max} caracteres...")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @NotBlank(message = "A descrição deve ser informada!")
    @Length(max = 500, message = "A descrição não deve ter mais que {max} caracteres...")
    @Column(name = "descricao", length = 500, nullable = false)
    private String descricao;
    @NotBlank(message = "A cidade deve ser informada!")
    @Length(max = 50, message = "A cidade não deve ter mais que {max} caracteres...")
    @Column(name = "cidade", length = 50, nullable = false)
    private String cidade;
    @Past(message = "O nascimento deve ser uma data no passado!")
    @NotNull(message = "A data de nascimento deve ser informada...")
    @Column(name = "nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar nascimento;
    @NotNull(message = "O peso deve ser informado!")
    @Min(value = 0, message = "O peso não pode ser negativo!")
    @Column(name = "peso", nullable = false, columnDefinition = "numeric(5,2)")
    private Double peso;
    @NotNull(message = "A altura deve ser informada!")
    @Min(value = 0, message = "A altura não pode ser negativa!")
    @Column(name = "altura", nullable = false, columnDefinition = "numeric(5,2)")
    private Double altura;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Pessoa> amigos = new ArrayList<>();
    
    public Pessoa() {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }
    
    //EQUALS E HASH CODE ESTÂO NO USUÀRIO

    /**
     * @param amigos the amigos to set
     */
    public void setAmigos(List<Pessoa> amigos) {
        this.amigos = amigos;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public List<Pessoa> getAmigos() {
        return amigos;
    }

}
