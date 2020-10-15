/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Venda;
import br.com.SisFarma.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    
public class VendaDAO {
    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    
    public boolean insert(Venda v) throws SQLException {
       sql = "INSERT INTO venda (quant, valor, id_usuario) VALUES (?,?,?)";
       
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,v.getQuant());
            stmt.setFloat(2,v.getValor());
            stmt.setInt(3,v.getU().getId());        
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        }
    public boolean delete(Venda v) throws SQLException{
            sql = "DELETE FROM venda WHERE id=?";
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,v.getId());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
   
    }
    
    public List<Venda> listar() throws SQLException{
         sql = "select v.id, v.quant, v.valor from venda v ";
         con = ConnectionFactory.getConnection();
         stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         
         List<Venda> vendas = new ArrayList<>();
         while (rs.next()){
             
             Venda v = new Venda();
             
             v.setId(rs.getInt("id"));
             v.setQuant(rs.getInt("quant"));
             v.setValor(rs.getFloat("valor"));
             
             vendas.add(v);
         }
         
         con.close();
         return vendas;
        
     }
}
