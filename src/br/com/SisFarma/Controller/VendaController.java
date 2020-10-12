/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.ProdutoDAO;
import br.com.SisFarma.dao.VendaDAO;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.gui.Vendas;
import br.com.SisFarma.model.Produto;
import br.com.SisFarma.model.Venda;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Matrix
 */
public class VendaController implements Initializable {
    
    @FXML private TableColumn<Produto, String> clmNome1;
    @FXML private TableView<Venda> tabela2;
    @FXML private TableView<Produto> tabela1;
    @FXML private TableColumn<Venda, String> clmNome2;
    @FXML private Button btSelecionar;
    @FXML private TableColumn<Produto, Long> clmId1;
    @FXML private TableColumn<Produto, Integer> clmQuant1;
    @FXML private TableColumn<Venda, Long> clmId2;
    @FXML private TableColumn<Venda, Integer> clmQuant2;
    @FXML private TextField txTotal;
    @FXML private Button btVender;
    @FXML private TableColumn<Produto, Float> clmPreco1;
    @FXML private TableColumn<Venda, Float> clmPreco2;
    @FXML private Button btVoltar;
    @FXML private TableColumn<Venda, Integer> clmCodigo2;
    @FXML private TableColumn<Produto, Integer> clmCodigo1;
    @FXML private TextField txQuant;
    @FXML private Button btRemover;
    private Produto selecionada;
    private Venda selecionada2;
    private float total = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initTable();
            //initTable2();
        } catch (SQLException ex) {
            Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabela1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
          @Override
          public void changed(ObservableValue observable, Object oldValue, Object newValue) {
              selecionada = (Produto) newValue;    
              Venda v = new Venda();
              v.setCodproduto(selecionada.getCodproduto());
              v.setNome(selecionada.getNome());
              v.setPreco(selecionada.getPreco());
              //txQuant.setText(String.valueOf(selecionada.getQuant()));
              v.setQuant(selecionada.getQuant());
              v.mostraVenda();
        
          }
      });
        
        tabela2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
          @Override
          public void changed(ObservableValue observable, Object oldValue, Object newValue) {
              selecionada2 = (Venda) newValue;    

          }
      });
        
        btVoltar.setOnMouseClicked((MouseEvent e) ->{
                fecha(); 
    });   
        
        btRemover.setOnMouseClicked((MouseEvent e) ->{
            try {
                removerVenda();
            } catch (SQLException ex) {
                Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });   
        
        btSelecionar.setOnMouseClicked((MouseEvent e) ->{
            try { 
                if(txQuant.getText().equals("")){
                    Alert al = new Alert(AlertType.WARNING);
                    al.setHeaderText("Digite a Quantidade");
                    al.show();
                }else{
                    selecionarProduto();
                    txQuant.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });   
    
}    

    private void initTable() throws SQLException {
        clmId1.setCellValueFactory(new PropertyValueFactory("id"));
        clmCodigo1.setCellValueFactory(new PropertyValueFactory("codproduto"));
        clmNome1.setCellValueFactory(new PropertyValueFactory("nome"));
        clmPreco1.setCellValueFactory(new PropertyValueFactory("preco"));
        clmQuant1.setCellValueFactory(new PropertyValueFactory("quant"));
        tabela1.setItems(atualizaTabela());
    }

    private ObservableList<Produto> atualizaTabela() throws SQLException {
        ProdutoDAO dao = new ProdutoDAO();
        return FXCollections.observableArrayList(dao.listar());
    }

    private void fecha() {
        MenuPrincipal m = new MenuPrincipal();
            Vendas.getStage().close();
            try {
                m.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void selecionarProduto() throws SQLException {
       if(selecionada != null){
           int novo;
           novo = Integer.parseInt(txQuant.getText());
            VendaDAO dao = new VendaDAO();
            Venda v = new Venda();
            v.setCodproduto(selecionada.getCodproduto());
            v.setNome(selecionada.getNome());
            v.setPreco(selecionada.getPreco() * novo);
            v.setQuant(novo);
            total += selecionada.getPreco();
            txTotal.setText(String.valueOf(total));
              
            if(dao.insert(v)){
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setHeaderText("Produto Selecionado para Venda");
                al.show();
                initTable2();
            }
 
        }else{
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setHeaderText("Nenhum Produto Selecionado");
            al.show();
        }
    }

    private void initTable2() throws SQLException {
        clmId2.setCellValueFactory(new PropertyValueFactory("id"));
        clmCodigo2.setCellValueFactory(new PropertyValueFactory("codproduto"));
        clmNome2.setCellValueFactory(new PropertyValueFactory("nome"));
        clmPreco2.setCellValueFactory(new PropertyValueFactory("preco"));
        clmQuant2.setCellValueFactory(new PropertyValueFactory("quant"));
        tabela2.setItems(atualizaTabela2());
    }

    private ObservableList<Venda> atualizaTabela2() throws SQLException {
        VendaDAO dao = new VendaDAO();
        return FXCollections.observableArrayList(dao.listar());
    }

    private void removerVenda() throws SQLException {
       if(selecionada2 != null){
            VendaDAO dao = new VendaDAO();
            dao.delete(selecionada2);
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setHeaderText("Removido com Sucesso");
            al.show();
            tabela2.setItems(atualizaTabela2());
        }else {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setHeaderText("Nenhum Produto Selecionado");
            al.show();
        }
    }
}
