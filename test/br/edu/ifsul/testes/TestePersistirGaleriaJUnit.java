/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.FotoID;
import br.edu.ifsul.modelo.Galeria;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Video;
import java.util.ArrayList;
import java.util.Calendar;
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
public class TestePersistirGaleriaJUnit {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirGaleriaJUnit() {
        
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
            Galeria g = new Galeria();
            List<Foto> fotos = em.createQuery("from Foto").getResultList();
            g.setFotos(fotos);
            g.setPessoa(em.find(Pessoa.class, 1));
            g.setDescricao("Galeria de fotos sobre futebol 2016");
            g.setTitulo("Futebol 2016");
            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }
}
