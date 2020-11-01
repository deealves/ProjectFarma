
package br.com.SisFarma.dao;

import br.com.SisFarma.model.Usuario;
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
 * @author Leticia
 */
public class UsuarioDAO {
    private Connection con;
    private String sql;
    private PreparedStatement stmt;
   
    public UsuarioDAO(){
        this.con = ConnectionFactory.getConnection();
    }
    
    public boolean insert(Usuario u) throws SQLException{
        sql = "INSERT INTO usuario (nome, cpf, usuario, senha) VALUES (?,?,?,?)";
      
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,u.getNome());
        stmt.setString(2, u.getCpf());
        stmt.setString(3,u.getUsuario());
        stmt.setString(4,u.getSenha());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
        
    }
    
     public boolean update(Usuario u) throws SQLException{
            sql = "UPDATE usuario SET nome = ?, cpf = ?, usuario = ?, senha = ? WHERE id=?";
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,u.getNome());
            stmt.setString(2, u.getCpf());
            stmt.setString(3,u.getUsuario());
            stmt.setString(4,u.getSenha());
            stmt.setInt(5,u.getId());
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
     
    }
     
     public boolean delete(Usuario u){
        sql = "DELETE FROM usuario WHERE id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,u.getId());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     public List<Usuario> listar() throws SQLException{
         sql = "select * from usuario ";
         con = ConnectionFactory.getConnection();
         stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         
         List<Usuario> usuarios = new ArrayList<>();
         while (rs.next()){
             
             Usuario u = new Usuario();
             
             u.setId(rs.getInt("id"));
             u.setNome(rs.getString("nome"));
             u.setCpf(rs.getString("cpf"));
             u.setUsuario(rs.getString("usuario"));
             u.setSenha(rs.getString("senha"));
             
             usuarios.add(u);
         }
         
         con.close();
         return usuarios;
        
     }
     
     public List<Usuario> buscar(String query) throws SQLException{
        List<Usuario> lista = new ArrayList<>();

        
        sql = "select p.* from produto p  where p.nome ilike ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, query + '%');
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt(1);
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            String usuario = rs.getString("usuario");
            String senha = rs.getString("senha");
            
            
            Usuario u = new Usuario();
            
            u.setId(id);
            u.setNome(nome);
            u.setCpf(cpf);
            u.setUsuario(usuario);
            u.setSenha(senha);
         
            lista.add(u);
        }
        con.close();
        return lista;
    }
     
    
}
