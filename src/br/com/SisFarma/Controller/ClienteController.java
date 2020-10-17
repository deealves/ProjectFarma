/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import static br.com.SisFarma.Controller.VendaController.getId_venda;
import br.com.SisFarma.gui.Clientes;
import br.com.SisFarma.model.Cliente;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.dao.ClienteDAO;
import br.com.SisFarma.dao.ClienteVendaDAO;
import br.com.SisFarma.dao.VendaDAO;
import br.com.SisFarma.model.ClienteVenda;
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
import javafx.event.ActionEvent;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class ClienteController implements Initializable {
    
    @FXML private TableView<Cliente> tableCliente;
    @FXML private TableColumn<Cliente, Integer> columnId;
    @FXML private TableColumn<Cliente, String> columnNome;
    @FXML private TableColumn<Cliente, String> columnCpf;
    @FXML private TableColumn<Cliente, String> columnRua;
    @FXML private TableColumn<Cliente, String> columnCidade;
    @FXML private TableColumn<Cliente, String> columnEstado;
    @FXML private TableColumn<Cliente, String> columnCep;
    @FXML private TableColumn<Cliente, String> columnTelefone;
    @FXML private TableColumn<Cliente, String> columnEmail;
    @FXML private Label labelCliente;
    @FXML private Button btEditar;
    @FXML private Button btRemover;
    @FXML private Label labelNome;
    @FXML private TextField textNome;
    @FXML private TextField textCpf;
    @FXML private TextField textRua;
    @FXML private TextField textCidade;
    @FXML private TextField textEstado;
    @FXML private TextField textTelefone;
    @FXML private TextField textEmail;
    @FXML private Label labelCpf;
    @FXML private Label labelRua;
    @FXML private Label labelCidade;
    @FXML private Label llabelEstado;
    @FXML private Label labelTelefone;
    @FXML private Label labelEmail;
    @FXML private Label lbId;
    @FXML private Button btCadastrar;
    @FXML private Button btVoltar;
    @FXML private ImageView imageCliente;
    @FXML private TextField textCep;
    @FXML private Label labelCep;
    Cliente selecionado;
    //private ClienteVenda teste;
    public static Button staticButton;
    public static Button staticRemover;
    public static Button staticEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        staticButton = btCadastrar;
        staticRemover = btRemover;
        staticEditar = btEditar;
        
        try {
            // TODO
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        textNome.setOnMouseClicked((Event e) ->{
            btCadastrar.setText("Cadastrar");
        });
        btCadastrar.setOnMouseClicked((Event e)->{
            if(btCadastrar.getText().equals("Realizar Venda")){
                try {
                    if(lbId.getText().equals("")){
                        if(textNome != null && textCpf != null){
                            cadastraCliente();
                            initTable();
                        }else{
                           Alert al = new Alert(AlertType.WARNING);
                           al.setHeaderText("Cadastre um Cliente ou Selecione um Cliente");
                           al.show(); 
                        }
                    }else{
                        realizarVenda();
                        abreMenu();
                        Alert al = new Alert(AlertType.CONFIRMATION);
                        al.setHeaderText("Venda Realizada!");
                        al.show();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                
                if(btCadastrar.getText().equals("Salvar")){
                    try {
                        salvar();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        cadastraCliente();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        btCadastrar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                if(btCadastrar.getText().equals("Realizar Venda")){
                    try {
                        realizarVenda();
                        abreMenu();
                        Alert al = new Alert(AlertType.CONFIRMATION);
                        al.setHeaderText("Venda Realizada!");
                        al.show();    
                    } catch (SQLException ex) {
                        Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }else{
                    try {
                        cadastraCliente();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        btVoltar.setOnMouseClicked((Event e)->{
            if(btVoltar.getText().equals("Cancelar")){
                limpaCampus();
                btCadastrar.setText("Cadastrar");
                btVoltar.setText("Voltar");
            }else{
                abreMenu();
            }
        
        });
        
        btVoltar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                if(btVoltar.getText().equals("Cancelar")){
                    limpaCampus();
                    btCadastrar.setText("Cadastrar");
                    btVoltar.setText("Voltar");
                }else{
                    abreMenu();
                }       
            }
        });
        
        btEditar.setOnMouseClicked((Event e) ->{
            if(staticEditar.getText().equals("Selecionar")){
                carregar();
                textNome.setDisable(true);
                textCpf.setDisable(true);
                textRua.setDisable(true);
                textCidade.setDisable(true);
                textEstado.setDisable(true);
                textCep.setDisable(true);
                textTelefone.setDisable(true);
                textEmail.setDisable(true);
                staticButton.setText("Realizar Venda");
            }else{
                editar();
            }
        });
                
        btRemover.setOnMouseClicked((Event e)->{
            try {
                remove();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        tableCliente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (Cliente) newValue;
            }
        });
    }
    
    public void cadastraCliente() throws SQLException{
        String nome = textNome.getText(),
               rua = textRua.getText(),
               cpf = textCpf.getText(),
               cidade = textCidade.getText(),
               estado = textEstado.getText(),
               cep = textCep.getText(),
               telefone = textTelefone.getText(),
               email = textEmail.getText();
        
        if(nome.equals("") && cpf.equals("") && rua.equals("") && cidade.equals("") && estado.equals("")
                && cep.equals("") && telefone.equals("") && email.equals("")){
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText ("Preencha todos os Campos");
            al.show();
            
        }else{
            Cliente c = new Cliente();
            c.setNome(nome);
            c.setCpf(cpf);
            c.setRua(rua);
            c.setCidade(cidade);
            c.setEstado(estado);
            c.setCep(cep);
            c.setTelefone(telefone);
            c.setEmail(email);

            ClienteDAO dao = new ClienteDAO();
            if(dao.inserir(c)){
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setHeaderText("Cliente Cadastrado!");
                al.show();
                tableCliente.setItems(atualizaTable());
                limpaCampus();
                tableCliente.setItems(atualizaTable());
            }else{
                Alert al = new Alert(AlertType.ERROR);
                al.setHeaderText("Cliente Não Cadastrado!");
                al.show();
            }
        }
        //abreMenu();
    }
    
    public void fecha(){
        Clientes.getStage().close();
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
    
    public void initTable() throws SQLException {
        columnId.setCellValueFactory(new PropertyValueFactory("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory("nome"));
        columnCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        columnRua.setCellValueFactory(new PropertyValueFactory("rua"));
        columnCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        columnEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        columnCep.setCellValueFactory(new PropertyValueFactory("cep"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tableCliente.setItems(atualizaTable());
    }
    
    public ObservableList<Cliente> atualizaTable() throws SQLException{
        ClienteDAO dao = new ClienteDAO();
        return FXCollections.observableArrayList(dao.listar());
    }
    
    public void remove() throws SQLException{
        if(selecionado == null){
            Alert al = new Alert(AlertType.WARNING);
            al.setHeaderText("Selecione um Cliente");
            al.show();
        }else{
            ClienteDAO dao = new ClienteDAO();
            dao.remover(selecionado);
            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setHeaderText("Cliente Removido com Sucesso");
            al.show();
            tableCliente.setItems(atualizaTable());
        }
    }
    
    public void salvar() throws SQLException{
        String nome = textNome.getText(),
               rua = textRua.getText(),
               cpf = textCpf.getText(),
               cidade = textCidade.getText(),
               estado = textEstado.getText(),
               cep = textCep.getText(),
               telefone = textTelefone.getText(),
               email = textEmail.getText();
       
        
        ClienteDAO dao = new ClienteDAO();
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setCpf(cpf);
        c.setRua(rua);
        c.setCidade(cidade);
        c.setEstado(estado);
        c.setCep(cep);
        c.setTelefone(telefone);
        c.setEmail(email);
        c.setId(selecionado.getId());
        
        if(dao.editar(c)){
            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setHeaderText ("Cliente Atualizado com Sucesso");
            al.show();
            tableCliente.setItems(atualizaTable());
            limpaCampus();
            btCadastrar.setText("Cadastrar");
            btVoltar.setText("Voltar");
            tableCliente.setItems(atualizaTable());
        }else{
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText ("Cliente Não Atualizado");
            al.show();
        }
    }
    
    public void editar(){
        if(selecionado != null){
            textNome.setText(selecionado.getNome());
            textCpf.setText(String.valueOf(selecionado.getCpf()));
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
            al.setHeaderText("Nenhum Cliente Selecionado");
            al.show();
        }
    }  
    
    public void limpaCampus(){
        textNome.setText("");
        textCpf.setText("");
        textRua.setText("");
        textCidade.setText("");
        textEstado.setText("");
        textCep.setText("");
        textTelefone.setText("");
        textEmail.setText("");
        textNome.setDisable(false);
        textCpf.setDisable(false);
        textRua.setDisable(false);
        textCidade.setDisable(false);
        textEstado.setDisable(false);
        textCep.setDisable(false);
        textTelefone.setDisable(false);
        textEmail.setDisable(false);
    }
    
    public void realizarVenda() throws SQLException{
        ClienteVendaDAO cv = new ClienteVendaDAO();
        ClienteVenda teste = new ClienteVenda();
        teste.getCliente().setId(selecionado.getId());
        teste.getVenda().setId(getId_venda());
        
        cv.insertClienteVenda(teste);
       
        
       /* try {
            cadastraCliente();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        
    }
    
    public void carregar(){
        if (selecionado != null) {
            textNome.setText(selecionado.getNome());
            textCpf.setText(selecionado.getCpf());
            textRua.setText(selecionado.getRua());
            textCidade.setText(selecionado.getCidade());
            textEstado.setText(selecionado.getEstado());
            textCep.setText(selecionado.getCep());
            textTelefone.setText(selecionado.getTelefone());
            textEmail.setText(selecionado.getEmail());
            lbId.setText(String.valueOf(selecionado.getId()));
            staticButton.setText("Realizar Venda");
            btVoltar.setText("Cancelar");
        } else {
            Alert al = new Alert(AlertType.WARNING);
            al.setHeaderText("Nenhum Cliente Selecionado");
            al.show();
        }
    }
   
}
