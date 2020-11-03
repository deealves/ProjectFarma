/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Venda;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author diego
 */
public class VendaDAOTest {
    VendaDAO dao;
    
    public VendaDAOTest() {
    }
    
    @Before
    public void setUp() {
        dao = new VendaDAO();
    }

    /**
     * Test of insert method, of class VendaDAO.
     */
    @Test
    public void testInsert() throws Exception {
        Venda v = new Venda();
        
        v.setId(10);
        v.setValor(30);
        v.setQuant(3);
        v.setData(LocalDate.parse("2020-11-03"));
        v.getU().setId(2);
        
        
        Assert.assertFalse(!(dao.insert(v)));
    }

    /**
     * Test of delete method, of class VendaDAO.
     */
    @Test
    public void testDelete() throws Exception {
        Venda u = new Venda();
        
        
        Assert.assertFalse(!(dao.delete(10)));
    }

    /**
     * Test of listar method, of class VendaDAO.
     */
    @Test
    public void testListar() throws Exception {
        List<Venda> teste = dao.listar();
        
        Assert.assertNotNull(teste);
    }

    /**
     * Test of listarQuantidadeVendasPorMes method, of class VendaDAO.
     */
    @Test
    public void testListarQuantidadeVendasPorMes() throws SQLException {
        
        Assert.assertNotNull(dao.listarValorVendasPorMes());
      
    }

    /**
     * Test of listarValorVendasPorMes method, of class VendaDAO.
     */
    @Test
    public void testListarValorVendasPorMes() {
        
        Assert.assertNotNull(dao.listarValorVendasPorMes());
    }
    
}
