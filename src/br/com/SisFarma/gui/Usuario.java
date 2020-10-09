/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.gui;

import static br.com.SisFarma.gui.Produto.setStage;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Leticia
 */
public class Usuario extends Application {
    
    private static Stage stage;//Uma Janela
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/br/com/SisFarma/view/Usuario.fxml"));//Carrega FXML
        
        Scene scene = new Scene(root);//Coloca o FXML em uma cena
        stage.setTitle("Usuario");
        stage.setScene(scene);//Coloca a cena em uma janela
        stage.show();//Abre a Janela
        setStage(stage);
}
    
     public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Usuario.stage = stage;
    }
    
}
