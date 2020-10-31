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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


public class ProdutoController implements Initializable {
    @FXML private TableColumn<Produto, String> clmFabricante;
    @FXML private Label lbId;
    @FXML private TableColumn<Produto, String> clmNome;
    @FXML private TableColumn<Produto, String> clmDescricao;
    @FXML private TextField txNome;
    @FXML private Button btVoltar;
    @FXML private TableView<Produto> tabela;
    @FXML private TableColumn<Produto, Float> clmPreco;
    @FXML private TableColumn<Produto, Integer> clmCodProduto;
    @FXML private TableColumn<Produto, Integer> clmQuant;
    @FXML private TextField txFabricante;
    @FXML private TextField txDescricao;
    @FXML private TextField txQuant;
    @FXML private Button btCadastrar;
    @FXML private TextField txPreco;
    @FXML private TextField txCodproduto;
    @FXML private Button btRemover;
    @FXML private Button btEditar;
    private Produto selecionada;

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
                txDescricao.setText("");
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
                  try {
                      salvarProduto();
                  } catch (SQLException ex) {
                      Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }else{
                  
                  try {
                      cadastrarProduto();
                  } catch (SQLException ex) {
                      Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
              }             
                   
        }); 
        
        tabela.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
          @Override
          public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionada = (Produto) newValue;            
            }
        });
        
        btRemover.setOnMouseClicked((MouseEvent e) ->{
            try {
                removerUsuario();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });  
        
        btEditar.setOnMouseClicked((MouseEvent e) ->{
            try {
                editarProduto();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        });
        
    }

    private void initTable() throws SQLException {
       
        clmCodProduto.setCellValueFactory(new PropertyValueFactory("codproduto"));
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        clmFabricante.setCellValueFactory(new PropertyValueFactory("fabricante"));
        clmDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
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

    private void salvarProduto() throws SQLException {
            int codproduto = Integer.parseInt(txCodproduto.getText());
            String nome = txNome.getText(); 
            float preco = Float.parseFloat(txPreco.getText());
            String fabricante = txFabricante.getText();
            String descricao = txDescricao.getText();
            int quant = Integer.parseInt(txQuant.getText());
            int id = Integer.parseInt(lbId.getText());

            ProdutoDAO dao = new ProdutoDAO();
            Produto p = new Produto();
            p.setCodproduto(codproduto);
            p.setNome(nome);
            p.setPreco(preco);
            p.setFabricante(fabricante);
            p.setDescricao(descricao);
            p.setQuant(quant);
            p.setId(id);
            if(dao.update(p)){
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setHeaderText ("Produto Atualizado");
                al.show();
                txCodproduto.setText("");
                txNome.setText("");
                txPreco.setText("");
                txFabricante.setText("");
                txDescricao.setText("");
                txQuant.setText("");
                lbId.setText(" ");
                btCadastrar.setText("Cadastrar");
                btVoltar.setText("Voltar");
                lbId.setText(" ");
                tabela.setItems(atualizaTabela());
                
          
            }else{
                Alert al = new Alert(AlertType.ERROR);
                al.setHeaderText ("Produto Não Atualizado");
                al.show();
            }
    }

    public void cadastrarProduto() throws SQLException{
    
        if(txCodproduto.getText().equals("") && txPreco.getText().equals("") && txQuant.getText().equals("")
                && txNome.getText().equals("") && txFabricante.getText().equals("") && txDescricao.getText().equals("")){
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText ("Preencha todos os Campos");
            al.show();
        }else{
            
            String nome = txNome.getText(); 
            String fabricante = txFabricante.getText();
            String descricao = txDescricao.getText();
            int codproduto = Integer.parseInt(txCodproduto.getText());
            float preco = Float.parseFloat(txPreco.getText());
            int quant = Integer.parseInt(txQuant.getText());

            Produto p = new Produto();
            p.setCodproduto(codproduto);
            p.setNome(nome);
            p.setPreco(preco);
            p.setFabricante(fabricante);
            p.setDescricao(descricao);
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
                txDescricao.setText("");
                txQuant.setText("");
                tabela.setItems(atualizaTabela());


            }else{
                Alert al = new Alert(AlertType.ERROR);
                al.setHeaderText ("Produto Não Cadastrado");
                al.show();
            }
        }
    }

    private void removerUsuario() throws SQLException {
       if(selecionada != null){
            ProdutoDAO dao = new ProdutoDAO();
            dao.delete(selecionada);
            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setHeaderText("Removido com Sucesso");
            al.show();
            tabela.setItems(atualizaTabela());
        }else {
            Alert al = new Alert(AlertType.WARNING);
            al.setHeaderText("Nenhum Produto Selecionado");
            al.show();
        }
    }

    public void editarProduto() throws SQLException{
        if(selecionada != null){
            lbId.setText(String.valueOf(selecionada.getId()));
            txCodproduto.setText(String.valueOf(selecionada.getCodproduto()));
            txNome.setText(selecionada.getNome());
            txPreco.setText(String.valueOf(selecionada.getPreco()));
            txFabricante.setText(selecionada.getFabricante());
            txDescricao.setText(selecionada.getDescricao());
            txQuant.setText(String.valueOf(selecionada.getQuant()));
            btCadastrar.setText("Salvar");
            btVoltar.setText("Cancelar");
            
            
        }else{
            Alert al = new Alert(AlertType.WARNING);
            al.setHeaderText("Nenhum Produto Selecionado");
            al.show();
        }
    }
    
}
