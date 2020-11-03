/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Cliente;
import br.com.SisFarma.model.Venda;
import br.com.SisFarma.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

    
public class VendaDAO {
    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    
    public boolean insert(Venda v) throws SQLException {
        sql = "INSERT INTO venda (quant, valor, data, id_usuario) VALUES (?,?,?,?)";
       
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1,v.getQuant());
        stmt.setFloat(2,v.getValor());
        stmt.setDate(3, Date.valueOf(v.getData()));
        stmt.setInt(4,v.getU().getId()); 
        
        stmt.execute();
        stmt.close();
        con.close();
        return true;
    }
    public boolean delete(int v) throws SQLException{
            sql = "delete from venda_produto where venda_produto.id_venda = ?;"
                    + "DELETE FROM venda WHERE venda.id=?";
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,v);
            stmt.setInt(2, v);
            stmt.execute();
            stmt.close();
            con.close();
            return true;
   
    }
    
    public List<Venda> listar() throws SQLException{
         sql = "select v.id, v.quant, v.valor, v.data from venda v ";
         con = ConnectionFactory.getConnection();
         stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         
         List<Venda> vendas = new ArrayList<>();
         while (rs.next()){
             
             Venda v = new Venda();
             
             v.setId(rs.getInt("id"));
             v.setQuant(rs.getInt("quant"));
             v.setValor(rs.getFloat("valor"));
             v.setData(rs.getDate("data").toLocalDate());
             
             vendas.add(v);
         }
         
         con.close();
         return vendas;
        
     }
    
    public Map<Integer, ArrayList> listarQuantidadeVendasPorMes(){
        sql = "select count(id), extract(year from data) as ano, extract(month from data) as mes from venda group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(rs.getInt("ano")))
                {
                    linha.add(rs.getInt("mes"));
                    linha.add(rs.getInt("count"));
                    retorno.put(rs.getInt("ano"), linha);
                }else{
                    ArrayList linhaNova = retorno.get(rs.getInt("ano"));
                    linhaNova.add(rs.getInt("mes"));
                    linhaNova.add(rs.getInt("count"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Map<Integer, ArrayList> listarValorVendasPorMes(){
        sql = "select sum(valor), extract(year from data) as ano, extract(month from data) as mes from venda group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(rs.getInt("ano")))
                {
                    linha.add(rs.getInt("mes"));
                    linha.add(rs.getInt("sum"));
                    retorno.put(rs.getInt("ano"), linha);
                }else{
                    ArrayList linhaNova = retorno.get(rs.getInt("ano"));
                    linhaNova.add(rs.getInt("mes"));
                    linhaNova.add(rs.getInt("sum"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
