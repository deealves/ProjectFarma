/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;


import br.com.SisFarma.dao.ClienteDAO;
import br.com.SisFarma.dao.FornecedorDAO;
import br.com.SisFarma.gui.Fornecedores;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.model.Cliente;
import br.com.SisFarma.model.Fornecedor;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leticia
 */
public class FornecedorController implements Initializable {
    
    @FXML private TableView<Fornecedor> tableFornecedores;
    @FXML private TableColumn<Fornecedor, String> columnNome;
    @FXML private TableColumn<Fornecedor, String> columnCnpj;  
    @FXML private TableColumn<Fornecedor, String> columnTelefone;
    @FXML private TableColumn<Fornecedor, String> columnEmail;
    @FXML private Button btEditar, btRemover, btCadastrar, btVoltar;
    @FXML private Label labelFornecedores,  labelNome, labelCidade, labelRua, labelCnpj, 
                        labelEstado, labelCep, labelTelefone, labelEmail;
    @FXML private TextField textNome,  textCnpj, textRua, textCidade, textCep, 
                            textEstado, textTelefone, textEmail;
    @FXML private TextField txBuscar;
    private Fornecedor selecionado;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
     
        btCadastrar.setOnMouseClicked((Event e)->{
            if(btCadastrar.getText().equals("Salvar")){
                salvarFornecedorEditado();
            }else{
                cadastrarFornecedor();
            }
        });
        
        btCadastrar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                if(btCadastrar.getText().equals("Salvar")){
                salvarFornecedorEditado();
                }else{
                    cadastrarFornecedor();
                }
            }
        });
        
        btVoltar.setOnMouseClicked((Event e)->{
            if(btVoltar.getText().equals("Cancelar")){
                limpaCampus();
                btCadastrar.setText("Cadastrar");
                btVoltar.setText("Voltar");
            }else{
                fecha();
                abreMenu();
            }
        });
        
        btVoltar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abreMenu();
            }
        });
        
        btEditar.setOnMouseClicked((Event e) ->{
            editarFornecedor();
        });
        
        txBuscar.setOnKeyReleased((KeyEvent e)-> {
            buscarProduto();
        });
                
        btRemover.setOnMouseClicked((Event e)->{
            removerFornecedor();
        });
        
        tableFornecedores.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (Fornecedor) newValue;
            }  
        });
    }
    
    public void cadastrarFornecedor(){
        String nome = textNome.getText(),
               cnpj = textCnpj.getText(),
               rua = textRua.getText(),
               cidade = textCidade.getText(),
               estado = textEstado.getText(),
               cep = textCep.getText(),
               telefone = textTelefone.getText(),
               email = textEmail.getText();
        if(nome.equals("") && cnpj.equals("") && rua.equals("") && cidade.equals("") && estado.equals("")
                && cep.equals("") && telefone.equals("") && email.equals("")){
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText ("Preencha todos os Campos");
            al.show();
        }else{
            Fornecedor f = new Fornecedor();
            f.setNome(nome);
            f.setCnpj(cnpj);
            f.setRua(rua);
            f.setCidade(cidade);
            f.setEstado(estado);
            f.setCep(cep);
            f.setTelefone(telefone);
            f.setEmail(email);

            FornecedorDAO dao = new FornecedorDAO();
            if(dao.inserir(f)){
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setHeaderText("Fornecedor Cadastrado!");
                al.show();
                tableFornecedores.setItems(atualizaTable());
                limpaCampus();
                tableFornecedores.setItems(atualizaTable());
            }else{
                Alert al = new Alert(AlertType.ERROR);
                al.setHeaderText("Fornecedor Não Cadastrado!");
                al.show();
            }
        }
        //abreMenu();
    }
    
    public void fecha(){
        Fornecedores.getStage().close();
    }
    
    public void abreMenu(){
        MenuPrincipal mp = new MenuPrincipal();
        fecha();
        try {
            mp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initTable() {
        columnNome.setCellValueFactory(new PropertyValueFactory("nome"));
        columnCnpj.setCellValueFactory(new PropertyValueFactory("cnpj")); 
        columnTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory("email"));

        tableFornecedores.setItems(atualizaTable());
    }
    
    public ObservableList<Fornecedor> atualizaTable(){
        FornecedorDAO dao = new FornecedorDAO();
        return FXCollections.observableList(dao.listar());
    }
    
    public void removerFornecedor(){
        if(selecionado == null){
            Alert al = new Alert(AlertType.WARNING);
            al.setHeaderText("Selecione um Fornecedor");
            al.show();
        }else{
            FornecedorDAO dao = new FornecedorDAO();
            dao.remover(selecionado);
            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setHeaderText("Fornecedor Removido com Sucesso");
            al.show();
            tableFornecedores.setItems(atualizaTable());
        }
    }
    
    public void salvarFornecedorEditado(){
        String nome = textNome.getText(),
               cnpj = textCnpj.getText(),
               rua = textRua.getText(),
               cidade = textCidade.getText(),
               estado = textEstado.getText(),
               cep = textCep.getText(),
               telefone = textTelefone.getText(),
               email = textEmail.getText();
        
        FornecedorDAO dao = new FornecedorDAO();
        Fornecedor f = new Fornecedor();
        f.setNome(nome);
        f.setCnpj(cnpj);
        f.setRua(rua);
        f.setCidade(cidade);
        f.setEstado(estado);
        f.setCep(cep);
        f.setTelefone(telefone);
        f.setEmail(email);
        f.setId(selecionado.getId());
        
        if(dao.editar(f)){
            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setHeaderText ("Fornecedor Atualizado com Sucesso");
            al.show();
            tableFornecedores.setItems(atualizaTable());
            limpaCampus();
            btCadastrar.setText("Cadastrar");
            btVoltar.setText("Voltar");
            tableFornecedores.setItems(atualizaTable());
        }else{
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText ("Fornecedor Não Atualizado");
            al.show();
        }
    }
    
    public void editarFornecedor(){
        if(selecionado != null){
            textNome.setText(selecionado.getNome());
            textCnpj.setText(selecionado.getCnpj());
            textRua.setText(selecionado.getRua());
            textCidade.setText(selecionado.getCidade());
            textEstado.setText(selecionado.getEstado());
            textCep.setText(selecionado.getCep());
            textTelefone.setText(selecionado.getTelefone());
            textEmail.setText(selecionado.getEmail());
            btCadastrar.setText("Salvar");
            btVoltar.setText("Cancelar");  
        }else{
            Alert al = new Alert(AlertType.WARNING);
            al.setHeaderText("Nenhum Fornecedor Selecionado");
            al.show();
        }
    }  
    
    public void limpaCampus(){
        textNome.setText("");
        textCnpj.setText("");
        textRua.setText("");
        textCidade.setText("");
        textEstado.setText("");
        textCep.setText("");
        textTelefone.setText("");
        textEmail.setText("");
    }

    private void buscarProduto() {
       String busca = txBuscar.getText();
        FornecedorDAO dao = new FornecedorDAO();
        try {
            List<Fornecedor> resultado = dao.buscar(busca);
            initTable2(busca);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void initTable2(String busca) throws SQLException {
        columnNome.setCellValueFactory(new PropertyValueFactory("nome"));
        columnCnpj.setCellValueFactory(new PropertyValueFactory("cnpj")); 
        columnTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tableFornecedores.setItems(buscaTabela(busca));
    }
    
    private ObservableList<Fornecedor> buscaTabela(String busca) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();
        return FXCollections.observableArrayList(dao.buscar(busca));
    }
 }
  

