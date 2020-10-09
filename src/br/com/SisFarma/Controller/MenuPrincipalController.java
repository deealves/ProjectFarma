/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.gui.Fornecedor;
import br.com.SisFarma.gui.Login;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.gui.Produto;
import br.com.SisFarma.gui.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leticia
 */
public class MenuPrincipalController implements Initializable {

    @FXML private Button btUsuario;
    @FXML private Button btFornecedor;
    @FXML private Button btProduto;
    @FXML private Button btCliente;
    @FXML private Button btVenda;
    @FXML private Button btDesconectar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btUsuario.setOnMouseClicked((MouseEvent e) ->{
            Usuario u = new Usuario();
            MenuPrincipal.getStage().close();
            try {
                u.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
        
        btFornecedor.setOnMouseClicked((MouseEvent e) ->{
            Fornecedor f = new Fornecedor();
            MenuPrincipal.getStage().close();
            try {
                f.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
        
        
        btProduto.setOnMouseClicked((MouseEvent e) ->{
            Produto pr = new Produto();
            MenuPrincipal.getStage().close();
            try {
                pr.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });

        btDesconectar.setOnMouseClicked((MouseEvent e) ->{
          fecha();
    });
      
    }
    
    public void fecha(){
            Login l = new Login();
            MenuPrincipal.getStage().close();
            try {
                l.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    

