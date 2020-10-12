/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.dao;


import br.com.SisFarma.model.Fornecedor;
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
public class FornecedorDAO {
    private final Connection con;
    private String sql;
    private PreparedStatement st;
    private ResultSet rs;

    public FornecedorDAO() {
        this.con = ConnectionFactory.getConnection();
    }
    
    
    
    public boolean inserir(Fornecedor fornecedor){
        //Inserir no banco de dados 
        sql = "INSERT INTO fornecedor(nome, cnpj, rua, cidade, estado, cep, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, fornecedor.getNome());
            st.setString(2, fornecedor.getCnpj());
            st.setString(3, fornecedor.getRua());
            st.setString(4, fornecedor.getCidade());
            st.setString(5, fornecedor.getEstado());
            st.setString(6, fornecedor.getCep());
            st.setString(7, fornecedor.getTelefone());
            st.setString(8, fornecedor.getEmail());
            st.execute();
            st.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
    
    public List<Fornecedor> listar() {
        //Listar clientes que estão no BD
        List<Fornecedor> fornecedores = new ArrayList<>();
        sql = "SELECT f.* FROM fornecedor f";
   
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
  
            while(rs.next()){
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setCodigo(rs.getInt("codigo"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));

                fornecedor.setRua(rs.getString("rua"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setEstado(rs.getString("estado"));
                fornecedor.setCep(rs.getString("cep"));

                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));

                fornecedores.add(fornecedor);
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return fornecedores;   
    }   
 
    public boolean editar(Fornecedor fornecedor){
        //Atualizar um cliente no BD
        sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, rua = ?, cidade = ?, estado = ?,"
                + "cep = ?, telefone = ?, email = ? WHERE codigo = ?";
  
        try {
            st = con.prepareStatement(sql);
            st.setString(1, fornecedor.getNome());
            st.setString(2, fornecedor.getCnpj());
            st.setString(3, fornecedor.getRua());
            st.setString(4, fornecedor.getCidade());
            st.setString(5, fornecedor.getEstado());
            st.setString(6, fornecedor.getCep());
            st.setString(7, fornecedor.getTelefone());
            st.setString(8, fornecedor.getEmail());
            st.setInt(9, fornecedor.getCodigo());
        
            st.execute();
            
            st.close();
            con.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }    
    }
    
    public Fornecedor buscar(int codigo) {
        //Buscar um cliente com determinado código no BD
        Fornecedor f = null;    
        sql = "SELECT f.* FROM fornecedor f WHERE f.codigo = ?";
        
        try {
            st = con.prepareStatement(sql);
            st.setInt(1, codigo);
        
            rs = st.executeQuery();

            if(rs.next()){
                String nome = rs.getString("nome");
                String cnpj = rs.getString("cnpj");
                String rua = rs.getString("rua");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String cep = rs.getString("cep");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");

                f = new Fornecedor();

                f.setCodigo(codigo);
                f.setNome(nome);
                f.setCnpj(cnpj);
                f.setRua(rua);
                f.setCidade(cidade);
                f.setEstado(estado);
                f.setCep(cep);
                f.setTelefone(telefone);
                f.setEmail(email);  
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return f;   
    }
    
    public List<Fornecedor> buscar(String query){
        //Buscar cliente com determinado nome no BD
        List<Fornecedor> lista = new ArrayList<>();
        sql = "SELECT f.* FROM fornecedor f WHERE c.nome ILIKE ?";
        
        try {
            st = con.prepareStatement(sql);
            st.setString(1, query + '%');
         
            rs = st.executeQuery();

            while(rs.next()){
               int codigo = rs.getInt(1); 
               String nome = rs.getString("nome");
               String cnpj = rs.getString("cnpj");
               String rua = rs.getString("rua");
               String cidade = rs.getString("cidade");
               String estado = rs.getString("estado");
               String cep = rs.getString("cep");
               String telefone = rs.getString("telefone");
               String email = rs.getString("email");

               Fornecedor f = new Fornecedor();

               f.setCodigo(codigo);
               f.setNome(nome);
               f.setCnpj(cnpj);
               f.setRua(rua);
               f.setCidade(cidade);
               f.setEstado(estado);
               f.setCep(cep);
               f.setTelefone(telefone);
               f.setEmail(email);  

               lista.add(f);
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lista;
    }
    
    public boolean remover(Fornecedor fornecedor){
        //Excluir um cliente 
        sql = "DELETE FROM fornecedor f WHERE f.codigo = ?";
        
        try {
            st = con.prepareStatement(sql);
            st.setInt(1, fornecedor.getCodigo());
        
            st.execute();
            
            st.close();
            con.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
