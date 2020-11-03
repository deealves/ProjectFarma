/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Usuario;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author diego
 */
public class UsuarioDAOTest {
    
    UsuarioDAO dao;
    
    public UsuarioDAOTest() {
    }
    
    @Before
    public void setUp() {
        dao = new UsuarioDAO();
    }

    /**
     * Test of insert method, of class UsuarioDAO.
     */
    @Test
    public void testInsert() throws Exception {
        Usuario u = new Usuario();
        
        u.setNome("Diego");
        u.setCpf("126.343.233-23");
        u.setUsuario("admin");
        u.setSenha("admin");
        
        Assert.assertFalse(!(dao.insert(u)));
    }

    /**
     * Test of update method, of class UsuarioDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        Usuario u = new Usuario();
        
        u.setNome("Diego");
        u.setCpf("126.343.233-23");
        u.setUsuario("diegobmed");
        u.setSenha("diego011");
        
        Assert.assertFalse(!(dao.update(u)));
    }

    /**
     * Test of delete method, of class UsuarioDAO.
     */
    @Test
    public void testDelete() {
        Usuario u = new Usuario();
        
        u.setId(1);
        
        Assert.assertFalse(!(dao.delete(u)));
    }

    /**
     * Test of listar method, of class UsuarioDAO.
     */
    @Test
    public void testListar() throws Exception {
        List<Usuario> teste = dao.listar();
        
        Assert.assertNotNull(teste);
    }

    /**
     * Test of buscar method, of class UsuarioDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        List<Usuario> teste = dao.buscar("admin");
        
        Assert.assertNotNull(teste);
    }
    
}
