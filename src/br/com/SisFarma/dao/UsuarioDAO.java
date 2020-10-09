
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
    
    public UsuarioDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public boolean insert(Usuario u){
        String sql = "INSERT INTO usuario (Nome, Email, Senha) VALUES (?,?,?);";
        con = ConnectionFactory.getConnection();
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
        String sql = "UPDATE Usuario SET nome = ?, email = ?, senha; ? WHERE id=?";
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
        String sql = "DELETE FROM Usuario WHERE id=?";
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
     
     public List<Usuario> getList(){
         List<Usuario> usuarios = new ArrayList<>();
         String sql = "SELECT * FROM Usuario";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getLong(""));//Essa string por parametro é o nome da coluna la do banco
                u.setNome(rs.getString("nome"));
                u.setNome(rs.getString("email"));
                u.setNome(rs.getString("senha"));
                usuarios.add(u);
            }
            stmt.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("ERRO, Lista não foi retornada");
            return null;
        }
        return usuarios;
     }
     
    
}
