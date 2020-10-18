/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import br.com.SisFarma.dao.VendaDAO;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class Teste2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        VendaDAO dao = new VendaDAO();
        
        System.out.println(dao.listar());
        
        System.out.println(dao.listarQuantidadeVendasPorMes());
    }
    
}
