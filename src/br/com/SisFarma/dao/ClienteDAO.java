/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Cliente;
import br.com.SisFarma.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ClienteDAO {
    private final Connection con;
    private String sql;
    private PreparedStatement st;
    private ResultSet rs;

    public ClienteDAO() {
        this.con = ConnectionFactory.getConnection();
    }
    
    
    
    public boolean inserir(Cliente cliente) throws SQLException{
        //Inserir no banco de dados 
        sql = "INSERT INTO cliente(nomeC, cpf, rua, cidade, estado, cep, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        
            st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, cliente.getNomeC());
            st.setString(2, cliente.getCpf());
            st.setString(3, cliente.getRua());
            st.setString(4, cliente.getCidade());
            st.setString(5, cliente.getEstado());
            st.setString(6, cliente.getCep());
            st.setString(7, cliente.getTelefone());
            st.setString(8, cliente.getEmail());
            st.execute();
            st.close();
            con.close();
            return true;
        
           
    }
    
    public List<Cliente> listar() throws SQLException {
        //Listar clientes que estão no BD
        List<Cliente> clientes = new ArrayList<>();
        sql = "SELECT c.* FROM cliente c";
   
       
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
  
            while(rs.next()){
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNomeC(rs.getString("nomeC"));
                cliente.setCpf(rs.getString("cpf"));

                cliente.setRua(rs.getString("rua"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCep(rs.getString("cep"));

                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));

                clientes.add(cliente);
            }
            st.close();
            rs.close();
            con.close();
        
        return clientes;   
    }   
 
    public boolean editar(Cliente cliente) throws SQLException{
        //Atualizar um cliente no BD
        sql = "UPDATE cliente SET nomeC = ?, cpf = ?, rua = ?, cidade = ?, estado = ?,"
                + "cep = ?, telefone = ?, email = ? WHERE id = ?";
  
     
            st = con.prepareStatement(sql);
            st.setString(1, cliente.getNomeC());
            st.setString(2, cliente.getCpf());
            st.setString(3, cliente.getRua());
            st.setString(4, cliente.getCidade());
            st.setString(5, cliente.getEstado());
            st.setString(6, cliente.getCep());
            st.setString(7, cliente.getTelefone());
            st.setString(8, cliente.getEmail());
            st.setInt(9, cliente.getId());
        
            st.execute();
            
            st.close();
            con.close();
            
            return true;
          
    }
    


     public List<Cliente> buscar(String query) throws SQLException{
        List<Cliente> lista = new ArrayList<>();


        
        sql = "select c.* from cliente c  where c.nomeC ilike ?";
        st = con.prepareStatement(sql);
        st.setString(1, query + '%');
    
        rs = st.executeQuery();

        while(rs.next()){
            int id = rs.getInt(1);
            String nomeC = rs.getString("nomeC");
            String cpf = rs.getString("cpf");
            String telefone = rs.getString("telefone");
            String rua = rs.getString("rua");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String cep = rs.getString("cep");
            String email = rs.getString("email");
            
            
            Cliente c = new Cliente();
            
            c.setId(id);
            c.setNomeC(nomeC);
            c.setCpf(cpf);
            c.setTelefone(telefone);
            c.setRua(rua);
            c.setCidade(cidade);
            c.setEstado(estado);
            c.setCep(cep);
            c.setEmail(email);
            
            
            lista.add(c);
        }
        con.close();

        return lista;
    }
    
    public boolean remover(Cliente cliente) throws SQLException{
        //Excluir um cliente 
        sql = "delete from cliente_venda where cliente_venda.id_cliente = ?;"
                + "DELETE FROM cliente c WHERE c.id = ?";
        
      
            st = con.prepareStatement(sql);
            st.setInt(1, cliente.getId());
            st.setInt(2, cliente.getId());
        
            st.execute();
            
            st.close();
            con.close();
            
            return true;
        
    }

   
}
