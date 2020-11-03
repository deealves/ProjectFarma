/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.ClienteDAO;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.model.Cliente;
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
public class RelatorioClienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<Cliente> tableRelatorioCliente;
    @FXML private TableColumn<Cliente, String> clmRelatorioClienteNome;
    @FXML private TableColumn<Cliente, String> clmRelatorioClienteCpf;
    @FXML private TableColumn<Cliente, String> clmRelatorioClienteRua;
    @FXML private TableColumn<Cliente, String> clmRelatorioClienteCidade;
    @FXML private TableColumn<Cliente, String> clmRelatorioClienteEstado;
    @FXML private TableColumn<Cliente, String> clmRelatorioClienteCep;
    @FXML private TableColumn<Cliente, String> clmRelatorioClienteTelefone;
    @FXML private TableColumn<Cliente, String> clmRelatorioClienteEmail;
    @FXML private Button btRelatorioClienteVoltar;
    @FXML private Button btRelatorioClienteImprimir;
    private Connection con;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btRelatorioClienteImprimir.setOnMouseClicked((MouseEvent e)->{
            try {
                imprimir();
            } catch (JRException ex) {
                Logger.getLogger(RelatorioClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btRelatorioClienteVoltar.setOnMouseClicked((MouseEvent e)->{
            abreMenu();
        });
    }

    private void initTable() throws SQLException {
        clmRelatorioClienteNome.setCellValueFactory(new PropertyValueFactory("nomeC"));
        clmRelatorioClienteCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        clmRelatorioClienteRua.setCellValueFactory(new PropertyValueFactory("rua"));
        clmRelatorioClienteCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        clmRelatorioClienteEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        clmRelatorioClienteCep.setCellValueFactory(new PropertyValueFactory("cep"));
        clmRelatorioClienteTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        clmRelatorioClienteEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tableRelatorioCliente.setItems(atualizaTabela());
    }

    private ObservableList<Cliente> atualizaTabela() throws SQLException {
        ClienteDAO dao = new ClienteDAO();
        return FXCollections.observableArrayList(dao.listar());
    }
    
    public void imprimir() throws JRException{
        URL url = getClass().getResource("/br/com/SisFarma/relatorios/projectFarmaClientes.jasper");
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
