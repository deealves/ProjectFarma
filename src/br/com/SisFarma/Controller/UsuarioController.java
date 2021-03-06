
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.UsuarioDAO;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.model.Usuario;
import br.com.SisFarma.gui.Usuarios;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class UsuarioController implements Initializable {
    
    @FXML private Button btVoltar;
    @FXML private TableView<Usuario> tabela;
    @FXML private TableColumn<Usuario, String> clmNome;
    @FXML private TableColumn<Usuario, String> clmCpf;
    @FXML private TextField txUsuario;
    @FXML private PasswordField txSenha;
    @FXML private TableColumn<Usuario, String> clmUsuario;
    @FXML private Button btEditar;
    @FXML private Button btRemover;
    @FXML private Button btCadastrar;
    @FXML private TextField txNome;
    @FXML private TextField txCpf;
    @FXML private Label lbId;
    @FXML private TextField txBuscar;
    private Usuario selecionada;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
      try {
          initTable();
      } catch (SQLException ex) {
          Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
        btVoltar.setOnMouseClicked((MouseEvent e) ->{
            if(btVoltar.getText().equals("Cancelar")){
                txNome.setText("");
                txUsuario.setText("");
                txSenha.setText("");
                txCpf.setText("");
                lbId.setText("");
                btCadastrar.setText("Cadastrar");
                btVoltar.setText("Voltar");
            }else{
                fecha();
            }
            
    });   
        
        btCadastrar.setOnMouseClicked((MouseEvent e) ->{
          try {
              if(btCadastrar.getText().equals("Salvar")){
                  salvarUsuario();
              }else{
                  cadastrarUsuario();
              }             
          } catch (SQLException ex) {
              Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
          }         
    }); 
        
        tabela.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
          @Override
          public void changed(ObservableValue observable, Object oldValue, Object newValue) {
              selecionada = (Usuario) newValue;            
          }
      });
        
        txBuscar.setOnKeyReleased((KeyEvent e)-> {
            buscarProduto();
        });
        
        btRemover.setOnMouseClicked((MouseEvent e) ->{
          try {
              removerUsuario();
          } catch (SQLException ex) {
              Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
          }
    });  
        btEditar.setOnMouseClicked((MouseEvent e) ->{
          try {
              editarUsuario();
          } catch (SQLException ex) {
              Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
    });
  }    
    
    public void initTable() throws SQLException{
        
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        clmUsuario.setCellValueFactory(new PropertyValueFactory("usuario"));
        tabela.setItems(atualizaTabela());
        
    }
    
    public ObservableList<Usuario> atualizaTabela() throws SQLException{
        UsuarioDAO dao = new UsuarioDAO();
        return FXCollections.observableArrayList(dao.listar());
    }
    
    public void removerUsuario() throws SQLException{
        if(selecionada != null){
            UsuarioDAO dao = new UsuarioDAO();
            dao.delete(selecionada);
            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setHeaderText("Removido com Sucesso");
            al.show();
            tabela.setItems(atualizaTabela());
        }else {
            Alert al = new Alert(AlertType.WARNING);
            al.setHeaderText("Nenhum Usuario Selecionado");
            al.show();
        }
    
    }
    public void salvarUsuario() throws SQLException{
            String nome = txNome.getText(),usuario = txUsuario.getText(),senha = txSenha.getText(), cpf = txCpf.getText();
            int id = Integer.parseInt(lbId.getText());

            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = new Usuario();
            u.setNome(nome);
            u.setCpf(cpf);
            u.setUsuario(usuario);
            u.setSenha(senha);
            u.setId(id);
            if(dao.update(u)){
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setHeaderText ("Usuario Cadastrado");
                al.show();
                txNome.setText("");
                txCpf.setText("");
                txUsuario.setText("");
                txSenha.setText("");
                btCadastrar.setText("Cadastrar");
                btVoltar.setText("Voltar");
                lbId.setText(" ");
                tabela.setItems(atualizaTabela());
                
          
            }else{
                Alert al = new Alert(AlertType.ERROR);
                al.setHeaderText ("Usuario Não Cadastrado");
                al.show();
            }   
    }
    public void editarUsuario() throws SQLException{
        if(selecionada != null){
            lbId.setText(String.valueOf(selecionada.getId()));
            txNome.setText(selecionada.getNome());
            txCpf.setText(selecionada.getCpf());
            txUsuario.setText(selecionada.getUsuario());
            txSenha.setText(selecionada.getSenha());
            btCadastrar.setText("Salvar");
            btVoltar.setText("Cancelar");
            
            
        }else{
            Alert al = new Alert(AlertType.WARNING);
            al.setHeaderText("Nenhum Usuario Selecionado");
            al.show();
        }
    }
    
    public void cadastrarUsuario() throws SQLException{
        String nome = txNome.getText(),
               cpf = txCpf.getText(),
               usuario = txUsuario.getText(), 
               senha = txSenha.getText();
        
        if(nome != null && cpf != null && usuario != null && senha != null){
            Usuario u = new Usuario(nome, cpf, usuario, senha);
            UsuarioDAO dao = new UsuarioDAO();
            
            if(dao.insert(u)){
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setHeaderText ("Usuario Cadastrado");
                al.show();
                txNome.setText("");
                txCpf.setText("");
                txUsuario.setText("");
                txSenha.setText("");
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
    
    public void fecha(){
            MenuPrincipal m = new MenuPrincipal();
            Usuarios.getStage().close();
            try {
                m.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    private void buscarProduto() {
     String busca = txBuscar.getText();
        UsuarioDAO dao = new UsuarioDAO();
        try {
            List<Usuario> resultado = dao.buscar(busca);
            initTable2(busca);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void initTable2(String busca) throws SQLException {
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        clmUsuario.setCellValueFactory(new PropertyValueFactory("usuario"));
        tabela.setItems(buscaTabela(busca));
    }
    
    private ObservableList<Usuario> buscaTabela(String busca) throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();
        return FXCollections.observableArrayList(dao.buscar(busca));
    }
    
}
