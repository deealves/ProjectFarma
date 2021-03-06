/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.ClienteVendaDAO;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.model.ClienteVendaProperty;
import br.com.SisFarma.util.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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
public class RelatorioVendaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<ClienteVendaProperty> tableRelatorioVenda;
    @FXML private TableColumn<ClienteVendaProperty, Integer> clmRelatorioVendaQuantidade;
    @FXML private TableColumn<ClienteVendaProperty, Float> clmRelatorioVendaValor;
    @FXML private TableColumn<ClienteVendaProperty, Date> clmRelatorioVendaData;
    @FXML private TableColumn<ClienteVendaProperty, String> clmRelatorioVendaVendedor;
    @FXML private TableColumn<ClienteVendaProperty, String> clmRelatorioVendaCliente;
    @FXML private TableColumn<ClienteVendaProperty, String> clmRelatorioVendaProduto;
    @FXML private Button btRelatorioVendaVoltar;
    @FXML private Button btRelatorioVendaImprimir;
    private Connection con;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btRelatorioVendaImprimir.setOnMouseClicked((MouseEvent e)->{
            try {
                imprimir();
            } catch (JRException ex) {
                Logger.getLogger(RelatorioProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btRelatorioVendaVoltar.setOnMouseClicked((MouseEvent e )->{
            abreMenu();
        });
    }
    
    private void initTable() throws SQLException {
        clmRelatorioVendaQuantidade.setCellValueFactory(new PropertyValueFactory("quant"));
        clmRelatorioVendaValor.setCellValueFactory(new PropertyValueFactory("valor"));
        clmRelatorioVendaData.setCellValueFactory(new PropertyValueFactory("data"));
        clmRelatorioVendaVendedor.setCellValueFactory(new PropertyValueFactory("nomeV"));
        clmRelatorioVendaCliente.setCellValueFactory(new PropertyValueFactory("nomeC"));
        clmRelatorioVendaProduto.setCellValueFactory(new PropertyValueFactory("produto"));
        tableRelatorioVenda.setItems(atualizaTabela());
    }

    private ObservableList<ClienteVendaProperty> atualizaTabela() throws SQLException {
        ClienteVendaDAO dao = new ClienteVendaDAO();
        return FXCollections.observableArrayList(dao.listar());
    }
    
     public void imprimir() throws JRException{
        URL url = getClass().getResource("/br/com/SisFarma/relatorios/projectFarmaVendas.jasper");
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
