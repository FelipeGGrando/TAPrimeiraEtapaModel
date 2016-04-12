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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "usuario")
//Classe abstrata que fornece herança
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotBlank(message = "O login deve ser informado!")
    @Length(max = 255, message = "O login não deve ter mais que {max} caracteres...")
    @Column(name = "login", length = 255, nullable = false)
    private String login;
    @NotBlank(message = "A senha deve ser informada!")
    @Length(max = 255, message = "A senha não deve ter mais que {max} caracteres...")
    @Column(name = "senha", length = 255, nullable = false)
    private String senha;
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
    @NotNull(message = "A data do último login deve ser informada...")
    @Column(name = "ultimo_login", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar ultimoLogin;
    
    public Usuario() {
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Calendar getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Calendar ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.login;
    }   
    
}
