/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
        System.out.println("Entrei");
    });
        
      btSair.setOnMouseClicked((MouseEvent e) ->{
        System.out.println("Sair");
    });
    }    
    
}
