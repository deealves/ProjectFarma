/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.FornecedorDAO;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.model.Fornecedor;
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
public class RelatorioFornecedorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<Fornecedor> tableRelatorioFornecedor;
    @FXML private TableColumn<Fornecedor, Integer> clmRelatorioFornecedorId;
    @FXML private TableColumn<Fornecedor, String> clmRelatorioFornecedorNome;
    @FXML private TableColumn<Fornecedor, String> clmRelatorioFornecedorCnpj;
    @FXML private TableColumn<Fornecedor, String> clmRelatorioFornecedorRua;
    @FXML private TableColumn<Fornecedor, String> clmRelatorioFornecedorCidade;
    @FXML private TableColumn<Fornecedor, String> clmRelatorioFornecedorEstado;
    @FXML private TableColumn<Fornecedor, String> clmRelatorioFornecedorCep;
    @FXML private TableColumn<Fornecedor, String> clmRelatorioFornecedorTelefone;
    @FXML private TableColumn<Fornecedor, String> clmRelatorioFornecedorEmail;
    @FXML private Button btRelatorioFornecedorVoltar;
    @FXML private Button btRelatorioFornecedorImprimir;
    private Connection con;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btRelatorioFornecedorImprimir.setOnMouseClicked((MouseEvent e)->{
            try {
                imprimir();
            } catch (JRException ex) {
                Logger.getLogger(RelatorioClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btRelatorioFornecedorVoltar.setOnMouseClicked((MouseEvent e)->{
            abreMenu();
        });
    }    
    private void initTable() throws SQLException {
        clmRelatorioFornecedorId.setCellValueFactory(new PropertyValueFactory("id"));
        clmRelatorioFornecedorNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmRelatorioFornecedorCnpj.setCellValueFactory(new PropertyValueFactory("cnpj"));
        clmRelatorioFornecedorRua.setCellValueFactory(new PropertyValueFactory("rua"));
        clmRelatorioFornecedorCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        clmRelatorioFornecedorEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        clmRelatorioFornecedorCep.setCellValueFactory(new PropertyValueFactory("cep"));
        clmRelatorioFornecedorTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        clmRelatorioFornecedorEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tableRelatorioFornecedor.setItems(atualizaTabela());
    }

    private ObservableList<Fornecedor> atualizaTabela() throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();
        return FXCollections.observableArrayList(dao.listar());
    }
    
    public void imprimir() throws JRException{
        URL url = getClass().getResource("/br/com/SisFarma/relatorios/projectFarmaFornecedores.jasper");
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
