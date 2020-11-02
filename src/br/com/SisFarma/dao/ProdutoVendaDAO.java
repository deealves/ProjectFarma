/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.ProdutoVenda;
import br.com.SisFarma.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Matrix
 */
public class ProdutoVendaDAO {
    
    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    
     public boolean insert(ProdutoVenda pv) throws SQLException {
        sql = "INSERT INTO venda_produto (id_venda, id_produto) VALUES (?,?)";
       
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, pv.getVenda().getId());
        stmt.setInt(2, pv.getProduto().getId());       
        stmt.execute();
        stmt.close();
        con.close();
        return true;
    }
}
