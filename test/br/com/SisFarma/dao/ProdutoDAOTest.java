/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Produto;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author diego
 */
public class ProdutoDAOTest {
    
    ProdutoDAO dao;
    
    public ProdutoDAOTest() {
    }
    
    @Before
    public void setUp() {
        dao = new ProdutoDAO();
    }

    /**
     * Test of insert method, of class ProdutoDAO.
     */
    @Test
    public void testInsert() throws Exception {
        Produto p = new Produto();
        
        p.setCodproduto(22345);
        p.setNome("Amoxicilina");
        p.setPreco(15);
        p.setFabricante("União Química Farmacêutica Nacional S.A");
        p.setDescricao("Recomendado para infecção urinária");
        p.setQuant(20); 
        
        Assert.assertFalse(!(dao.insert(p)));
    }

    /**
     * Test of update method, of class ProdutoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        Produto p = new Produto();
        
        p.setId(2);
        p.setCodproduto(454343);
        p.setNome("Cloroquina");
        p.setPreco(40);
        p.setFabricante("Apsen");
        p.setDescricao("Recomendada para afecções reumáticas");
        p.setQuant(20); 
        
        Assert.assertFalse(!(dao.insert(p)));
    }

    /**
     * Test of delete method, of class ProdutoDAO.
     */
    @Test
    public void testDelete() throws Exception {
        Produto p = new Produto();
        
        p.setId(1);
        
        Assert.assertFalse(!(dao.delete(p)));
    }

    /**
     * Test of listar method, of class ProdutoDAO.
     */
    @Test
    public void testListar() throws Exception {
        List<Produto> teste = dao.listar();
        
        Assert.assertNotNull(teste);
    }

    /**
     * Test of buscar method, of class ProdutoDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        List<Produto> teste = dao.buscar("Cloroquina");
        
        Assert.assertNotNull(teste);
    }
    
}
