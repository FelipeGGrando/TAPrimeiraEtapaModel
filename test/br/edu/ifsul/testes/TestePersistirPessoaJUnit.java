/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;
import br.edu.ifsul.modelo.Pessoa;
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
            List<Pessoa> amigos = new ArrayList<>();
            amigos.add(amigo);
            Calendar c = Calendar.getInstance();
            Pessoa p = new Pessoa();
            p.setLogin("FelipeGGrando");
            p.setSenha("123456");
            p.setAtivo(true);
            p.setUltimoLogin(c);
            p.setNome("Felipe Gasparin Grando");
            p.setDescricao("Cara muito legal e tal...");
            p.setCidade("Passo Fundo");
            p.setNascimento(new GregorianCalendar(1994, Calendar.MARCH, 26));
            p.setPeso(63.00);
            p.setAltura(1.64);
            p.setAmigos(amigos);
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
