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
import br.com.SisFarma.gui.Vendas;
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
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    @FXML private MenuItem menuItemVendas;
    @FXML private MenuItem menuItemValor;
    @FXML private MenuItem menuItemRelatorioProduto;
    @FXML private MenuItem menuItemRelatorioCliente;
    @FXML private AnchorPane anchorPane;
    @FXML private Pane pane;
    
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
        
         btVenda.setOnMouseClicked((MouseEvent e) ->{
            Vendas v = new Vendas();
            MenuPrincipal.getStage().close();
            try {
                v.start(new Stage());
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
        
        menuItemRelatorioProduto.setOnAction((ActionEvent e)->{
            try {
                handleMenuItemRelatorioProduto(e);
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        menuItemRelatorioCliente.setOnAction((ActionEvent e)->{
            try {
                handleMenuItemRelatorioCliente(e);
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        

}
    
    @FXML
    public void handleMenuItemGraficoVendasPorMes(ActionEvent e) throws IOException{ 
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/com/SisFarma/view/GraficoVendasPorMes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGraficoValorVendas(ActionEvent e) throws IOException{ 
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/com/SisFarma/view/GraficoVendasValor.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatorioProduto(ActionEvent e) throws IOException{ 
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/com/SisFarma/view/RelatorioProduto.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatorioCliente(ActionEvent e) throws IOException{ 
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/com/SisFarma/view/RelatorioCliente.fxml"));
        anchorPane.getChildren().setAll(a);
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


    

