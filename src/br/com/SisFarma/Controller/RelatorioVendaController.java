/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.ClienteVendaDAO;
import br.com.SisFarma.dao.VendaDAO;
import br.com.SisFarma.model.Cliente;
import br.com.SisFarma.model.ClienteVenda;
import br.com.SisFarma.model.Venda;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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

/**
 * FXML Controller class
 *
 * @author diego
 */
public class RelatorioVendaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<ClienteVenda> tableRelatorioVenda;
    @FXML private TableColumn<ClienteVenda, Integer> clmRelatorioVendaId;
    @FXML private TableColumn<ClienteVenda, Integer> clmRelatorioVendaQuantidade;
    @FXML private TableColumn<ClienteVenda, Float> clmRelatorioVendaValor;
    @FXML private TableColumn<ClienteVenda, Date> clmRelatorioVendaData;
    @FXML private TableColumn<ClienteVenda, String> clmRelatorioVendaVendedor;
    @FXML private TableColumn<ClienteVenda, String> clmRelatorioVendaCliente;
    @FXML private Button btRelatorioVendaVoltar;
    @FXML private Button btRelatorioVendaImprimir;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initTable() throws SQLException {
        clmRelatorioVendaId.setCellValueFactory(new PropertyValueFactory("id"));
        clmRelatorioVendaQuantidade.setCellValueFactory(new PropertyValueFactory("quant"));
        clmRelatorioVendaValor.setCellValueFactory(new PropertyValueFactory("valor"));
        clmRelatorioVendaData.setCellValueFactory(new PropertyValueFactory("data"));
        clmRelatorioVendaVendedor.setCellValueFactory(new PropertyValueFactory("nome"));
        clmRelatorioVendaCliente.setCellValueFactory(new PropertyValueFactory("nomeC"));
        tableRelatorioVenda.setItems(atualizaTabela());
    }

    private ObservableList<ClienteVenda> atualizaTabela() throws SQLException {
        ClienteVendaDAO dao = new ClienteVendaDAO();
        return FXCollections.observableArrayList(dao.listar());
    }
    
}
