
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.UsuarioDAO;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.model.Usuario;
import br.com.SisFarma.gui.Usuarios;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class UsuarioController implements Initializable {
    
  @FXML
    private Button btVoltar;

    @FXML
    private TableView<Usuario> tabela;

    @FXML
    private TableColumn<Usuario, String> clmNome;

    @FXML
    private TextField txUsuario;

    @FXML
    private PasswordField txSenha;

    @FXML
    private TableColumn<Usuario, String> clmUsuario;

    @FXML
    private TableColumn<Usuario, Long> clmId;

    @FXML
    private Button btEditar;

    @FXML
    private Button btRemover;

    @FXML
    private Button btCadastrar;

    @FXML
    private TextField txNome;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
      try {
          initTable();
      } catch (SQLException ex) {
          Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
      }
        btVoltar.setOnMouseClicked((MouseEvent e) ->{
            fecha();
    });   
        btCadastrar.setOnMouseClicked((MouseEvent e) ->{
            cadastrarUsuario();      
    });     
    }    
    
    public void initTable() throws SQLException{
        clmId.setCellValueFactory(new PropertyValueFactory("id"));
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmUsuario.setCellValueFactory(new PropertyValueFactory("email"));
        tabela.setItems(atualizaTabela());
        
    }
    
    public ObservableList<Usuario> atualizaTabela() throws SQLException{
        UsuarioDAO dao = new UsuarioDAO();
        return FXCollections.observableArrayList(dao.listar());
    }
    
    public void cadastrarUsuario(){
        String nome = txNome.getText(), 
               email = txUsuario.getText(), 
               senha = txSenha.getText();
        
        if(nome != null && email != null && senha != null){
            Usuario u = new Usuario(nome,email,senha);
            UsuarioDAO dao = new UsuarioDAO();
            
            if(dao.insert(u)){
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setHeaderText ("Usuario Cadastrado");
                al.show();
                txNome.setText("");
                txUsuario.setText("");
                txSenha.setText("");
          
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
    
}
