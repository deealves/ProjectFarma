/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.Controller;

import br.com.SisFarma.dao.VendaDAO;
import br.com.SisFarma.gui.MenuPrincipal;
import br.com.SisFarma.util.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class GraficoVendasValorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private AnchorPane anchorPane;
    @FXML private BarChart<String, Integer> barChartValor;
    @FXML private CategoryAxis categoryAxisValor;
    @FXML private NumberAxis numberAxisValor;
    @FXML private Button btVoltar;
    
    private ObservableList<String> observableListMeses = FXCollections.observableArrayList();
    
    private Connection con;
    private final VendaDAO dao = new VendaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println(menuItem.getText());
            /*barChartVPM.setTitle("Valor de Vendas");
            barChartVPM.setVisible(true);
            categoryAxisVPM.setLabel("Meses");
            categoryAxisVPM.setVisible(true);
            numberAxisVPM.setLabel("Valor das Vendas");
            numberAxisVPM.setVisible(true);*/
        String[] arrayMeses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
        // Converte o array em uma lista e adiciona em nossa ObservableList de meses.
        observableListMeses.addAll(Arrays.asList(arrayMeses));

        // Associa os nomes de mês como categorias para o eixo horizontal.
        categoryAxisValor.setCategories(observableListMeses);
        
        con = ConnectionFactory.getConnection();
            
        Map<Integer, ArrayList> dados = dao.listarValorVendasPorMes();
        for (Map.Entry<Integer, ArrayList> dadosItem : dados.entrySet()) {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(dadosItem.getKey().toString());

            for (int i = 0; i < dadosItem.getValue().size(); i = i + 2) {
                String mes;
                Integer quantidade;

                mes = retornaNomeMes((int) dadosItem.getValue().get(i));
                quantidade = (Integer) dadosItem.getValue().get(i + 1);

                series.getData().add(new XYChart.Data<>(mes, quantidade));
            }
            barChartValor.getData().add(series);

        }
        btVoltar.setOnMouseClicked((MouseEvent e )->{
            abreMenu();
        });
    }

    public String retornaNomeMes(int mes) {
        switch (mes) {
            case 1:
                return "Jan";
            case 2:
                return "Fev";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "Mai";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Set";
            case 10:
                return "Out";
            case 11:
                return "Nov";
            case 12:
                return "Dez";
            default:
                return "";
        }
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
