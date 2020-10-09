/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import br.com.SisFarma.dao.UsuarioDAO;
import br.com.SisFarma.model.Usuario;

/**
 *
 * @author Leticia
 */
public class Teste {

    
    public static void main(String[] args) {
        Usuario u = new Usuario();
        u.setNome("Dennys");
        u.setEmail("deealves");
        u.setSenha("saude100");
        
        UsuarioDAO dao = new UsuarioDAO();
        if(dao.insert(u)){
            System.out.println("cadastrado");
        }
        else{
            System.out.println("não cadastrado");
        }
        
    }
    
}
