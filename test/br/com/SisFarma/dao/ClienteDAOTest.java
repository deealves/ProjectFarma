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
import static org.junit.Assert.*;

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
     * @throws java.lang.Exception
     */
    @Test
    public void testInserir() throws SQLException {
        Cliente c = new Cliente();
        
       
        c.setNome("Irineu");
        c.setCpf("1233");
        c.setCep("5555");
        c.setCidade("SM");
        c.setEstado("PB");
        c.setRua("Janu");
        c.setEmail("ddd@");
        c.setTelefone("5323");
        
        Assert.assertFalse(!(dao.inserir(c)));
        
    }

    /**
     * Test of listar method, of class ClienteDAO.
     */
    @Test
    public void testListar() throws SQLException {
        List<Cliente> teste = dao.listar();
        
        Assert.assertNotNull(teste);
          
    }

    /**
     * Test of editar method, of class ClienteDAO.
     */
    @Test
    public void testEditar() throws SQLException{
        Cliente c = new Cliente();
       
        c.setId(2);
        c.setNome("Joao");
        c.setCpf("123");
        c.setCep("5555");
        c.setCidade("SM");
        c.setEstado("PB");
        c.setRua("Janu");
        c.setEmail("ddd@");
        c.setTelefone("5323");
        
        Assert.assertFalse(!(dao.editar(c)));
    }

    /**
     * Test of buscar method, of class ClienteDAO.
     */
    @Test
    public void testBuscar_int() throws SQLException{
        Cliente teste = dao.buscar(10);
        
        Assert.assertNotNull(teste);
    }

    /**
     * Test of buscar method, of class ClienteDAO.
     */
    @Test
    public void testBuscar_String() throws SQLException{
        List<Cliente> teste = dao.buscar("Irineu");
        
        Assert.assertNotNull(teste);
    }

    /**
     * Test of remover method, of class ClienteDAO.
     */
    @Test
    public void testRemover() throws SQLException{
        
        Cliente c = new Cliente();
        
        c.setId(12);
        
        Assert.assertFalse(!(dao.remover(c)));
    }
    
}
