/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.UsuarioDAO;
import br.com.SisFarma.gui.Login;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
          try {
              logar();
          } catch (SQLException ex) {
              Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
          }
    });
        
      btSair.setOnMouseClicked((MouseEvent e) ->{
        fecha();
    });
      
      btEntrar.setOnKeyPressed((KeyEvent e) ->{
        if(e.getCode() == KeyCode.ENTER){
            try {
                logar();
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
      
      txSenha.setOnKeyPressed((KeyEvent e) ->{
        if(e.getCode() == KeyCode.ENTER){
            try {
                logar();
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    }    
    
    public void fecha(){
        Login.getStage().close();
    }
    
    public void logar() throws SQLException{
        
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.listar();
        for(int x = 0; x< usuarios.size(); x++){
            if(txEmail.getText().equals(usuarios.get(x).getEmail()) && txSenha.getText().equals(usuarios.get(x).getSenha())){
                MenuPrincipal p = new MenuPrincipal();
                x = usuarios.size();
                fecha();
                try {
                    p.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }else{
                if(x == usuarios.size()-1){
                    Alert al = new Alert(AlertType.ERROR);              
                    al.setHeaderText("Login Incorreto");
                    al.setContentText("Usuario ou Senha Incorreto");
                    al.show();  
                }
            }
        }
    }   
}
