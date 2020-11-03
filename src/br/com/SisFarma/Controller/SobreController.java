/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.gui.Sobre;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class SobreController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    @FXML private Button btOk;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btOk.setOnMouseClicked((MouseEvent e)->{
            fecha();
        });
    }    
    
    public void fecha(){
        
        Sobre.getStage().close();
    }
    
    public void abreMenu(){
        MenuPrincipal mp = new MenuPrincipal();
        try {
            mp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
