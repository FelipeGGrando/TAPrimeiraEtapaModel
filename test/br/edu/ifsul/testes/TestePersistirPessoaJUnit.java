/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;
import br.edu.ifsul.modelo.Pessoa;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class TestePersistirPessoaJUnit {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirPessoaJUnit() {
        
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
            Pessoa amigo = em.find(Pessoa.class, 1);
            Calendar c = Calendar.getInstance();
            Pessoa p = new Pessoa();
            p.getAmigos().add(amigo);
            p.setLogin("Felipe");
            p.setSenha("felipe");
            p.setAtivo(true);
            p.setUltimoLogin(c);
            p.setNome("Felipe Gasparin");
            p.setDescricao("Cara muito legal tal...");
            p.setCidade("Marau");
            p.setAltura(1.65);
            p.setPeso(70.00);
            p.setNascimento(new GregorianCalendar(1994, Calendar.MARCH, 26));
            p.setPeso(63.00);
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }
}
