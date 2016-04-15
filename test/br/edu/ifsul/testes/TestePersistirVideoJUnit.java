/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

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
public class TestePersistirVideoJUnit {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirVideoJUnit() {

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
//            Comentario c = em.find(Comentario.class, 1);
//            List<Comentario> comentarios = new ArrayList<>();
//            comentarios.add(c);
            Calendar publicacao = Calendar.getInstance();
            Video v = new Video();
            v.setLink("https://www.youtube.com/watch?v=8rmBvkMrjAY");
            v.setPublicacao(publicacao);
            v.setTitulo("Best 100 Goals In Football History");
            v.setPublico(true);
            v.setQuantidadeVisualizacoes(15000);
//            v.setComentarios(comentarios);
//            c.setVideo(v);
            em.getTransaction().begin();
            em.persist(v);
//            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }
}
