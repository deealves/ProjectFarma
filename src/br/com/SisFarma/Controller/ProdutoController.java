/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.ProdutoDAO;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.gui.Produtos;
import br.com.SisFarma.model.Produto;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Leticia
 */
public class ProdutoController implements Initializable {
    @FXML private TableColumn<Produto, String> clmFabricante;
    @FXML private Label lbId;
    @FXML private TableColumn<Produto, String> clmNome;
    @FXML private TextField txNome;
    @FXML private Button btVoltar;
    @FXML private TableView<Produto> tabela;
    @FXML private TableColumn<Produto, Float> clmPreco;
    @FXML private TableColumn<Produto, Long> clmId;
    @FXML private TableColumn<Produto, Integer> clmCodProduto;
    @FXML private TableColumn<Produto, Integer> clmQuant;
    @FXML private TextField txFabricante;
    @FXML private TextField txQuant;
    @FXML private Button btCadastrar;
    @FXML private TextField txPreco;
    @FXML private TextField txCodproduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btVoltar.setOnMouseClicked((MouseEvent e) ->{
            if(btVoltar.getText().equals("Cancelar")){
                txCodproduto.setText("");
                txNome.setText("");
                txPreco.setText("");
                txFabricante.setText("");
                txQuant.setText("");
                lbId.setText(" ");
                btCadastrar.setText("Cadastrar");
                btVoltar.setText("Voltar");
            }else{
                fecha();
            }
            
    });   
        
        btCadastrar.setOnMouseClicked((MouseEvent e) ->{
          
              if(btCadastrar.getText().equals("Salvar")){
                  salvarProduto();
              }else{
                  try {
                      cadastrarProduto();
                  } catch (SQLException ex) {
                      Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }             
                   
    }); 
    }

    private void initTable() throws SQLException {
       clmId.setCellValueFactory(new PropertyValueFactory("id"));
        clmCodProduto.setCellValueFactory(new PropertyValueFactory("codproduto"));
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        clmFabricante.setCellValueFactory(new PropertyValueFactory("Fabricante"));
        clmQuant.setCellValueFactory(new PropertyValueFactory("quant"));
        tabela.setItems(atualizaTabela());
    }

    private ObservableList<Produto> atualizaTabela() throws SQLException {
        ProdutoDAO dao = new ProdutoDAO();
        return FXCollections.observableArrayList(dao.listar());
    }

    private void fecha() {
        MenuPrincipal m = new MenuPrincipal();
            Produtos.getStage().close();
            try {
                m.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void salvarProduto() {
        
    }

    private void cadastrarProduto() throws SQLException {
        int codproduto = Integer.parseInt(txCodproduto.getText());
        String nome = txNome.getText(); 
        float preco = Float.parseFloat(txPreco.getText());
        String fabricante = txFabricante.getText(); 
        int quant = Integer.parseInt(txQuant.getText());
       
        
        if(nome != null){
            Produto p = new Produto();
            p.setCodproduto(codproduto);
            p.setNome(nome);
            p.setPreco(preco);
            p.setFabricante(fabricante);
            p.setQuant(quant);
            ProdutoDAO dao = new ProdutoDAO();
            
            if(dao.insert(p)){
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setHeaderText ("Produto Cadastrado");
                al.show();
                txCodproduto.setText("");
                txNome.setText("");
                txPreco.setText("");
                txFabricante.setText("");
                txQuant.setText("");
                tabela.setItems(atualizaTabela());
                
          
            }else{
                Alert al = new Alert(AlertType.ERROR);
                al.setHeaderText ("Usuario Não Cadastrado");
                al.show();
            }
            
        } else{
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText ("Preencha todos os Campos");
            al.show();
        }
    }
    
}
