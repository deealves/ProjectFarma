/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import br.com.SisFarma.dao.UsuarioDAO;
import br.com.SisFarma.model.Produto;
import br.com.SisFarma.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class Teste {

    
    public static void main(String[] args) throws SQLException {
        Produto p = new Produto();
        
        
        
        System.out.println("Listar Usuario");
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = new UsuarioDAO().listar();
        for(int x = 0; x< usuarios.size();x++){
            usuarios.get(x).toString();
        }
   
        
    }
    
}
