/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.FotoID;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Video;
import java.util.Calendar;
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
            fotoId.setNumero(1);
            Foto f = new Foto();
            f.setPublicacao(publicacao);
            f.setFotoId(fotoId);
            f.setPublico(true);
            f.setQuantidadeVisualizacoes(15000);
            f.setTitulo("Eu e meus pais");
            f.setEndereco("https://pixabay.com/photo-1092508/");
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
