/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Cliente;
import br.com.SisFarma.model.ClienteVenda;
import br.com.SisFarma.model.ClienteVendaProperty;
import br.com.SisFarma.model.ProdutoVenda;
import br.com.SisFarma.model.Usuario;
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
    private static ClienteVendaProperty cp;
    
    
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
    
   public List<ClienteVendaProperty> listar() throws SQLException{
        sql = "select venda.quant, round(cast(venda.valor as numeric), 2), venda.data, usuario.nome, cliente.nomeC, STRING_AGG(produto.nome, ',') as produto\n" +
        "from produto, venda, cliente, cliente_venda, usuario, venda_produto where venda.id = venda_produto.id_venda \n" +
        "and produto.id = venda_produto.id_produto and venda.id = cliente_venda.id_venda\n" +
        "and cliente_venda.id_cliente = cliente.id and venda.id_usuario = usuario.id\n" +
        "group by (venda.quant, venda.valor, venda.data, usuario.nome, cliente.nomeC) order by venda.data;";
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();

        List<ClienteVendaProperty> vendas = new ArrayList<>();
        while (rs.next()){
            int quant = rs.getInt("quant");
            System.out.println(quant);
            float valor = rs.getFloat("round");
            System.out.println(valor);
            LocalDate data = rs.getDate("data").toLocalDate();
            System.out.println(data);
            String nomeV = rs.getString("nome");
            System.out.println(nomeV);
            String nomeC = rs.getString("nomeC");
            System.out.println(nomeC);
            String produto = rs.getString("produto");
            
            ClienteVenda cv = new ClienteVenda();
            Venda v = new Venda();
            Cliente c = new Cliente();
            ProdutoVenda pv = new ProdutoVenda();
            
            
            v.setQuant(quant);
            v.setValor(valor);
            v.setData(data);
            v.getU().setNome(nomeV);
            
            c.setNomeC(nomeC);
            
            pv.getProduto().setNome(produto);
            
            cv.setCliente(c);
            cv.setVenda(v);
   
            cp = new ClienteVendaProperty(v, c, pv);

            vendas.add(cp);
            
        }

        con.close();
        return vendas;
   }
}
