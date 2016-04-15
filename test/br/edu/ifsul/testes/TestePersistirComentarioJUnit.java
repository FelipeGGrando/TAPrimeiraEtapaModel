/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Comentario;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Video;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Felipe
 */
public class TestePersistirComentarioJUnit {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirComentarioJUnit() {
        
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TAPrimeiraEtapaModelPU");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste() {
        boolean exception = false;
        try {
            Comentario c = new Comentario();
            Video v = em.find(Video.class, 1);
            Pessoa p = em.find(Pessoa.class, 1);
            Calendar publicacao = Calendar.getInstance();
            c.setPublicacao(publicacao);
            c.setPessoa(p);
            c.setConteudo("Muito legal esse vídeo cara!");
            c.setVideo(v);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }
}
