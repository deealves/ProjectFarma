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

                fornecedor.setId(rs.getInt("id"));
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
                + "cep = ?, telefone = ?, email = ? WHERE id = ?";
  
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
            st.setInt(9, fornecedor.getId());
        
            st.execute();
            
            st.close();
            con.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }    
    }
   
    
    public List<Fornecedor> buscar(String query) throws SQLException{
        List<Fornecedor> lista = new ArrayList<>();

        
        sql = "select f.* from fornecedor f  where f.nome ilike ?";
        st = con.prepareStatement(sql);
        st.setString(1, query + '%');
        
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt(1);
            String nome = rs.getString("nome");
            String cnpj = rs.getString("cnpj");
            String telefone = rs.getString("telefone");
            String rua = rs.getString("rua");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String cep = rs.getString("cep");
            String email = rs.getString("email");
            
            
            Fornecedor f = new Fornecedor();
           
            f.setId(id);
            f.setNome(nome);
            f.setCnpj(cnpj);
            f.setTelefone(telefone);
            f.setRua(rua);
            f.setCidade(cidade);
            f.setEstado(estado);
            f.setCep(cep);
            f.setEmail(email);
            
            
            lista.add(f);
        }
        con.close();
        return lista;
    }
    
    public boolean remover(Fornecedor fornecedor){
        //Excluir um cliente 
        sql = "DELETE FROM fornecedor f WHERE f.id = ?";
        
        try {
            st = con.prepareStatement(sql);
            st.setInt(1, fornecedor.getId());
        
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
