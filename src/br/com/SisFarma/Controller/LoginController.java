/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.gui.Login;
import br.com.SisFarma.gui.MenuPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Leticia
 */
public class LoginController implements Initializable {
    
    
    @FXML
    private Button btEntrar;

    @FXML
    private PasswordField txSenha;

    @FXML
    private Label label;

    @FXML
    private Button btSair;

    @FXML
    private TextField txEmail;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btEntrar.setOnMouseClicked((MouseEvent e) ->{
            logar();
    });
        
      btSair.setOnMouseClicked((MouseEvent e) ->{
        fecha();
    });
      
      btEntrar.setOnKeyPressed((KeyEvent e) ->{
        if(e.getCode() == KeyCode.ENTER){
            logar();
        }
    });
      
      txSenha.setOnKeyPressed((KeyEvent e) ->{
        if(e.getCode() == KeyCode.ENTER){
            logar();
        }
    });
    }    
    
    public void fecha(){
        Login.getStage().close();
    }
    
    public void logar(){
        if(txEmail.getText().equals("deealves") && txSenha.getText().equals("saude100")){
            MenuPrincipal p = new MenuPrincipal();
            fecha();
            try {
                p.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Login Incorreto");
            alert.setContentText("Usuario ou Senha Incorreto");
        }
    }
            
    
}
