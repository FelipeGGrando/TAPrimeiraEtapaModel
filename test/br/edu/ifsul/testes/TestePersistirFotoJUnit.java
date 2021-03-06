/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.FotoID;
import br.edu.ifsul.modelo.Galeria;
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
public class TestePersistirFotoJUnit {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirFotoJUnit() {
        
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
            Calendar publicacao = Calendar.getInstance();
            FotoID fotoId = new FotoID();
            fotoId.setGaleria(em.find(Galeria.class, 1));
            fotoId.setNumero(1);
            Foto f = em.find(Foto.class, fotoId);
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }
}
