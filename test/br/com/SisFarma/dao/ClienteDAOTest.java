/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Cliente;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class ClienteDAOTest {
    ClienteDAO dao;
 
    public ClienteDAOTest() {
        
        
    }
    
    @Before
    public void setUp() {
        dao = new ClienteDAO();
        
    }
 
    /**
     * Test of inserir method, of class ClienteDAO.
     * @throws java.sql.SQLException
     */
    @Test
    public void testInserir() throws SQLException {
        Cliente c = new Cliente();
        
       
        c.setNomeC("Marcio Medeiros");
        c.setCpf("232.313.474-23");
        c.setCep("55625-000");
        c.setCidade("São Mamede");
        c.setEstado("PB");
        c.setRua("Janúncio Nóbrega");
        c.setEmail("marcio.medeiros@gmail.com");
        c.setTelefone("(83) 98623-3940");
        
        Assert.assertFalse(!(dao.inserir(c)));
        
    }

    /**
     * Test of listar method, of class ClienteDAO.
     * @throws java.sql.SQLException
     */
    @Test
    public void testListar() throws SQLException {
        List<Cliente> teste = dao.listar();
        
        Assert.assertNotNull(teste);
          
    }

    /**
     * Test of editar method, of class ClienteDAO.
     * @throws java.sql.SQLException
     */
    @Test
    public void testEditar() throws SQLException{
        Cliente c = new Cliente();
       
        c.setId(1);
        c.setNomeC("Erick Garcia");
        c.setCpf("123.562.483-12");
        c.setCep("58625-000");
        c.setCidade("São Mamede");
        c.setEstado("PB");
        c.setRua("Lindemberge Trindade");
        c.setEmail("erickgarcia@gmail.com");
        c.setTelefone("(81) 99962-3530");
        
        Assert.assertFalse(!(dao.editar(c)));
    }
    
     /**
     * Test of buscar method, of class ClienteDAO.
     * @throws java.sql.SQLException
     */
    
    @Test
    public void testBuscar_String() throws SQLException{
        List<Cliente> teste = dao.buscar("João");
        
        Assert.assertNotNull(teste);
    }

    /**
     * Test of remover method, of class ClienteDAO.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRemover() throws SQLException{
        
        Cliente c = new Cliente();
        
        c.setId(3);
        
        Assert.assertFalse(!(dao.remover(c)));
    }
    
}
