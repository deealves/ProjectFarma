/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import static br.com.SisFarma.Controller.LoginController.getId_usuario;
import br.com.SisFarma.dao.ProdutoDAO;
import br.com.SisFarma.dao.VendaDAO;
import br.com.SisFarma.gui.Clientes;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.gui.Vendas;
import br.com.SisFarma.model.ClienteVenda;
import br.com.SisFarma.model.Produto;
import br.com.SisFarma.model.Venda;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
public class VendaController extends ClienteController implements Initializable {
    
    @FXML private TableColumn<Produto, String> clmNome1;
    @FXML private TableView<Produto> tabela2;
    @FXML private TableView<Produto> tabela1;
    @FXML private TableColumn<Produto, String> clmNome2;
    @FXML private Button btSelecionar;
    @FXML private TableColumn<Produto, Integer> clmId1;
    @FXML private TableColumn<Produto, Integer> clmQuant1;
    @FXML private TableColumn<Produto, Integer> clmId2;
    @FXML private TableColumn<Produto, Integer> clmQuant2;
    @FXML private TextField txTotal;
    @FXML private Button btVender;
    @FXML private TableColumn<Produto, Float> clmPreco1;
    @FXML private TableColumn<Produto, Float> clmPreco2;
    @FXML private Button btVoltar;
    @FXML private TableColumn<Produto, Integer> clmCodigo2;
    @FXML private TableColumn<Produto, Integer> clmCodigo1;
    @FXML private TextField txQuant;
    @FXML private Button btRemover;
    private Produto selecionada;
    private Produto selecionada2;
    private float total = 0;
    private int novo, quant2, valorAtual = 0;;
    private final  Locale locale = new Locale("pt", "BR");
    private final  NumberFormat dinheiro;
    private int teste;
    private float teste2;
    private final List<Produto> produto = new ArrayList<>();
    private Venda venda;
    private static int id_venda;

    public static int getId_venda() {
        return id_venda;
    }

    public static void setId_venda(int id_venda) {
        VendaController.id_venda = id_venda;
    }

    public VendaController() {
        this.venda = new Venda();
        this.dinheiro = NumberFormat.getCurrencyInstance(locale);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            initTable();
            total = 0;
            //initTable2();
        } catch (SQLException ex) {
            Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabela1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionada = (Produto) newValue;    
          

            }
        });
        
        tabela2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionada2 = (Produto) newValue;    

            }
        });
        
        btVoltar.setOnMouseClicked((MouseEvent e) ->{
            fecha(); 
        });   
        
        btRemover.setOnMouseClicked((MouseEvent e) ->{
            try {
                removerVenda();
                System.out.println("Teste: "+teste);
                total = total - teste2;
                System.out.println("Remover:" +total);
                
                txTotal.setText(String.valueOf(dinheiro.format(total)));
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
        
        btVender.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Clientes novo = new Clientes();
                Vendas.getStage().close();
                try {
                    vender();
                    
                    ClienteController c = new ClienteController();
                    /*c.btEditar.setText("Selecionar");
                    c.btRemover.setText("");
                    c.btRemover.disableProperty();
                    c.btRemover.setBackground(Background.EMPTY);*/
                    novo.start(new Stage());
                    //staticButton.setText("Realizar Venda");
                    staticRemover.setDisable(true);
                    staticRemover.setVisible(false);
                    staticEditar.setText("Selecionar");
                    
                    //if(c.realizarVenda(venda))
                } catch (Exception ex) {
                    Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    
}    

    public void initTable() throws SQLException {
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

    public void fecha() {
        MenuPrincipal m = new MenuPrincipal();
        Vendas.getStage().close();
        try {
            m.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selecionarProduto() throws SQLException {
        if(selecionada != null){    
            novo = Integer.parseInt(txQuant.getText());
            Produto p = new Produto();
            p.setId(selecionada.getId());
            p.setCodproduto(selecionada.getCodproduto());
            p.setNome(selecionada.getNome());
            p.setPreco(selecionada.getPreco() * novo);
            p.setFabricante(selecionada.getFabricante());
            p.setDescricao(selecionada.getDescricao());
            quant2 = selecionada.getQuant();
            System.out.println(p.getQuant());
            System.out.println(quant2);
            valorAtual = quant2 - novo;
            System.out.println(valorAtual);
            if(valorAtual < 0){
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setHeaderText("Produto Sem Estoque");
                al.show();
            }else{
                p.setQuant(novo);
                if(produto.add(p)){
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                    al.setHeaderText("Produto Selecionado para Venda");
                    al.show();
                    initTable2();
                    total = total + p.getPreco();
                    txTotal.setText(String.valueOf(dinheiro.format(total)));
                    System.out.println(total);
                    ProdutoDAO dao = new ProdutoDAO();
                    p.setQuant(valorAtual);
                    if(dao.update(p)){
                        initTable();
                    }
                }
            
                

                
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

    private ObservableList<Produto> atualizaTabela2() throws SQLException {
        return FXCollections.observableArrayList(produto);
    }

    private void removerVenda() throws SQLException {
       if(selecionada2 != null){
            teste = selecionada2.getQuant();
            teste2 = selecionada2.getPreco();
           
            produto.remove(selecionada2);
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
    
    private void vender() throws SQLException{ 
        VendaDAO dao = new VendaDAO();
        ClienteVenda cv = new ClienteVenda();
        float valorAux = 0;
        int quantAux = 0;
        for(int i = 0; i < produto.size(); i++){
            valorAux += produto.get(i).getPreco();
            quantAux += produto.get(i).getQuant();
            
        }
        venda.setValor(valorAux);
        venda.setQuant(quantAux);
        venda.getU().setId(getId_usuario());
       
        dao.insert(venda);
        
        for(int i = 0; i < dao.listar().size(); i++){
           id_venda = dao.listar().get(i).getId(); 
        }
        
        System.out.println(id_venda);
    }
}
