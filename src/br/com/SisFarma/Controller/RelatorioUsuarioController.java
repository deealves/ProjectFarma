/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.UsuarioDAO;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.model.Usuario;
import br.com.SisFarma.util.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class RelatorioUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<Usuario> tableRelatorioUsuario;
    @FXML private TableColumn<Usuario, String> clmRelatorioUsuarioNome;
    @FXML private TableColumn<Usuario, String> clmRelatorioUsuarioCpf;
    @FXML private TableColumn<Usuario, String> clmRelatorioUsuarioUsuario;
    @FXML private TableColumn<Usuario, String> clmRelatorioUsuarioSenha;
    @FXML private Button btRelatorioUsuarioVoltar;
    @FXML private Button btRelatorioUsuarioImprimir;
    private Connection con;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btRelatorioUsuarioImprimir.setOnMouseClicked((MouseEvent e)->{
            try {
                imprimir();
            } catch (JRException ex) {
                Logger.getLogger(RelatorioProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btRelatorioUsuarioVoltar.setOnMouseClicked((MouseEvent e )->{
            abreMenu();
        });
    }    
    
    private void initTable() throws SQLException {
        clmRelatorioUsuarioNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmRelatorioUsuarioCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        clmRelatorioUsuarioUsuario.setCellValueFactory(new PropertyValueFactory("usuario"));
        clmRelatorioUsuarioSenha.setCellValueFactory(new PropertyValueFactory("senha"));
        tableRelatorioUsuario.setItems(atualizaTabela());
    }

    private ObservableList<Usuario> atualizaTabela() throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();
        return FXCollections.observableArrayList(dao.listar());
    }
    
    public void imprimir() throws JRException{
        URL url = getClass().getResource("/br/com/SisFarma/relatorios/projectFarmaUsuarios.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);
        
        con =  ConnectionFactory.getConnection();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
    
    public void fecha(){
        MenuPrincipal.getStage().close();
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
}
