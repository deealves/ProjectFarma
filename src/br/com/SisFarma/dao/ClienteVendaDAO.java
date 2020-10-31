/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Cliente;
import br.com.SisFarma.model.ClienteVenda;
import br.com.SisFarma.model.Venda;
import br.com.SisFarma.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author diego
 */
public class ClienteVendaDAO {
    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    
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
    
   public List<ClienteVenda> listar() throws SQLException{
        sql = "select venda.id, venda.quant, venda.valor, venda.data, usuario.nome, cliente.nome from venda, cliente, cliente_venda, usuario where venda.id = cliente_venda.id_venda and cliente_venda.id_cliente = cliente.id and venda.id_usuario = usuario.id order by venda.valor";
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();

        List<ClienteVenda> vendas = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            int quant = rs.getInt("quant");
            float valor = rs.getFloat("valor");
            LocalDate data = rs.getDate("data").toLocalDate();
            String nomeV = rs.getString("nome");
            String nomeC = rs.getString("nome");
            
            ClienteVenda cv = new ClienteVenda();
            Venda v = new Venda();
            Cliente c = new Cliente();
          
            v.setQuant(quant);
            v.setValor(valor);
            v.setData(data);
            v.getU().setNome(nomeV);
            
            c.setNome(nomeC);
            
            cv.setId(id);
            cv.setCliente(c);
            cv.setVenda(v);

            vendas.add(cv);
        }

        con.close();
        return vendas;
   }
}
