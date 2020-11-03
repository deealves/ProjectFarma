/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Fornecedor;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author diego
 */
public class FornecedorDAOTest {
    
    FornecedorDAO dao;
    
    public FornecedorDAOTest() {
    }
    
    @Before
    public void setUp() {
        dao = new FornecedorDAO();
    }

    /**
     * Test of inserir method, of class FornecedorDAO.
     */
    @Test
    public void testInserir() {
        Fornecedor f = new Fornecedor();
        
       
        f.setNome("NeoQuímica");
        f.setCnpj("05.161.069/0001-10");
        f.setCep("54400-370");
        f.setCidade("Jaboatão dos Guararapes");
        f.setEstado("PE");
        f.setRua("Zelindo Marafante");
        f.setEmail("neoquimica@gmail.com");
        f.setTelefone("0800 9799 900");
        
        Assert.assertFalse(!(dao.inserir(f)));
    }

    /**
     * Test of listar method, of class FornecedorDAO.
     */
    @Test
    public void testListar() {
        List<Fornecedor> teste = dao.listar();
        
        Assert.assertNotNull(teste);
    }

    /**
     * Test of editar method, of class FornecedorDAO.
     */
    @Test
    public void testEditar() {
        Fornecedor f = new Fornecedor();
       
        f.setId(2);
        f.setNome("Eurofarma");
        f.setCnpj("61.190.096/0001-92");
        f.setCep("50781-600");
        f.setCidade("Recife");
        f.setEstado("PE");
        f.setRua("Três Carneiros Alto");
        f.setEmail("euroatende@eurofarma.com.br");
        f.setTelefone("(81) 4106-5346");
        
        Assert.assertFalse(!(dao.editar(f)));
    }

    /**
     * Test of buscar method, of class FornecedorDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        List<Fornecedor> teste = dao.buscar("GloblaFarma");
        
        Assert.assertNotNull(teste);
    }

    /**
     * Test of remover method, of class FornecedorDAO.
     */
    @Test
    public void testRemover() {
        Fornecedor f = new Fornecedor();
        
        f.setId(3);
        
        Assert.assertFalse(!(dao.remover(f)));
    }
    
}
