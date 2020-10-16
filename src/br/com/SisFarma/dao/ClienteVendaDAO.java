/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.ClienteVenda;
import br.com.SisFarma.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class ClienteVendaDAO {
    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    
    
    public boolean insertClienteVenda(ClienteVenda cv) throws SQLException{
        sql = "INSERT INTO cliente_venda (id_cliente, id_venda) VALUES (?,?)";
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, cv.getCliente().getId());
        stmt.setInt(2, cv.getVenda().getId());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
    }
}
