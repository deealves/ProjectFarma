
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
        this.con = new ConnectionFactory().getConnection();
    }
    
    public boolean insert(Usuario u){
        sql = "INSERT INTO usuario (nome,email,senha) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,u.getNome());
            stmt.setString(2,u.getEmail());
            stmt.setString(3,u.getSenha());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public boolean update(Usuario u){
        sql = "UPDATE usuario SET nome = ?, email = ?, senha; ? WHERE id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,u.getNome());
            stmt.setString(2,u.getEmail());
            stmt.setString(3,u.getSenha());
            stmt.setLong(4,u.getId());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     public boolean delete(Usuario u){
        sql = "DELETE FROM usuario WHERE id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,u.getId());
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
         sql = "select u.id, u.nome, u.email, u.senha from usuario u ";
         con = ConnectionFactory.getConnection();
         stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         
         List<Usuario> usuarios = new ArrayList<>();
         while (rs.next()){
             
             Usuario u = new Usuario();
             
             u.setId(rs.getLong("id"));
             u.setNome(rs.getString("nome"));
             u.setEmail(rs.getString("email"));
             u.setSenha(rs.getString("senha"));
             
             usuarios.add(u);
         }
         
         con.close();
         return usuarios;
        
     }
     
    
}
