/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.gui.Clientes;
import br.com.SisFarma.gui.Fornecedores;
import br.com.SisFarma.gui.Login;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.gui.Produtos;
import br.com.SisFarma.gui.Usuarios;
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
            Usuarios u = new Usuarios();
            MenuPrincipal.getStage().close();
            try {
                u.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
        
        btFornecedor.setOnMouseClicked((MouseEvent e) ->{
            Fornecedores f = new Fornecedores();
            MenuPrincipal.getStage().close();
            try {
                f.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
        
        
        btProduto.setOnMouseClicked((MouseEvent e) ->{
            Produtos pr = new Produtos();
            MenuPrincipal.getStage().close();
            try {
                pr.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
        
        btCliente.setOnMouseClicked((MouseEvent e) ->{
            Clientes c = new Clientes();
            MenuPrincipal.getStage().close();
            try {
                c.start(new Stage());
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
    

