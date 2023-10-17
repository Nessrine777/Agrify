/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import entities.*;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import services.StockDiversCrud;


/**
 * FXML Controller class
 *
 * @author Ahmed Raies
 */
public class StockDiversController implements Initializable {

    @FXML
    private Button btmenusta;
    @FXML
    private Button btmenustp;
    @FXML
    private Button btmenusd;
    @FXML
    private TableView<StockDivers> tablevsd;
    @FXML
    private TableColumn<StockDivers, Integer> colidsd;
    @FXML
    private TableColumn<StockDivers, NomDivStock> colnomsd;
    @FXML
    private TableColumn<StockDivers, Health> colhealthsd;
    @FXML
    private TableColumn<StockDivers, Float> colquantitesd;
    @FXML
    private TableColumn<StockDivers, Date> coldatesd;
    @FXML
    private ComboBox<NomDivStock> cbnomsd;
    @FXML
    private ComboBox<Health> cbhealthsd;
    @FXML
    private TextField tfquantitesd;
    @FXML
    private DatePicker datesd;
    @FXML
    private Button btajsd;
    @FXML
    private Button btmodifsd;
    @FXML
    private Button btsuppsd;
        private ObservableList<StockDivers> sdList = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<NomDivStock> nomsdList = FXCollections.observableArrayList(NomDivStock.values());
        cbnomsd.setItems(nomsdList);
        
        ObservableList<Health> healthList = FXCollections.observableArrayList(Health.values());
        cbhealthsd.setItems(healthList);
        
        colidsd.setCellValueFactory(new PropertyValueFactory<>("idSD"));
        colnomsd.setCellValueFactory(new PropertyValueFactory<>("nomSD"));
        colhealthsd.setCellValueFactory(new PropertyValueFactory<>("healthSD"));
        colquantitesd.setCellValueFactory(new PropertyValueFactory<>("quantiteSD"));
        coldatesd.setCellValueFactory(new PropertyValueFactory<>("DateEntreeStock"));
        StockDiversCrud sdc = new StockDiversCrud();
        sdList.addAll(sdc.afficherStockDivers()); 
        tablevsd.setItems(sdList);
        
       //Selection 
        tablevsd.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedSD) -> {
    if (selectedSD != null) {
        cbnomsd.setValue(selectedSD.getNomSD());
        cbhealthsd.setValue(selectedSD.getHealthSD());
        tfquantitesd.setText(String.valueOf(selectedSD.getQuantiteSD()));
        
        java.util.Date utilDate = selectedSD.getDateEntreeStock();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        datesd.setValue(sqlDate.toLocalDate());  
    }
});
      
    }    

    @FXML
    private void addSD(ActionEvent event) {
                
    NomDivStock nomSD = cbnomsd.getValue();
    Health healthSD= cbhealthsd.getValue();
    float quantiteSD  = Float.parseFloat(tfquantitesd.getText());
    LocalDate localDate = datesd.getValue();
    Date DateEntreeStock = Date.valueOf(localDate);

        
    StockDivers sd = new StockDivers(nomSD, quantiteSD, healthSD, DateEntreeStock);    
    StockDiversCrud  sdc = new StockDiversCrud ();
    sdc.ajouterStockDivers(sd);
    
    sdList.add(sd);
    
    tablevsd.setItems(sdList);
    }

    @FXML
    private void modifSD(ActionEvent event) {
        StockDivers selectedSD = tablevsd.getSelectionModel().getSelectedItem();

      if (selectedSD != null) {
        // Récupérez les nouvelles valeurs des champs du formulaire
        NomDivStock newNomSD = cbnomsd.getValue();
        Health newHealthSD = cbhealthsd.getValue();
        float newQuantiteSD = Float.parseFloat(tfquantitesd.getText());
        LocalDate localDate = datesd.getValue();
        Date newDateEntreeStock = Date.valueOf(localDate);

        // Mettez à jour les propriétés de l'objet sélectionné
        selectedSD.setNomSD(newNomSD);
        selectedSD.setHealthSD(newHealthSD);
        selectedSD.setQuantiteSD(newQuantiteSD);
        selectedSD.setDateEntreeStock(newDateEntreeStock);

        // Mettez à jour la ligne sélectionnée dans la TableView
        int selectedIndex = tablevsd.getSelectionModel().getSelectedIndex();
        sdList.set(selectedIndex, selectedSD);
        
        StockDiversCrud sdc = new StockDiversCrud();
        sdc.modifierStockDivers(selectedSD);

        // Réinitialisez les champs du formulaire
        cbnomsd.getSelectionModel().clearSelection();
        cbhealthsd.getSelectionModel().clearSelection();
        tfquantitesd.clear();
        datesd.getEditor().clear();
    }

    }

    @FXML
    private void suppSD(ActionEvent event) {
        StockDivers selectedSD = tablevsd.getSelectionModel().getSelectedItem();

      if (selectedSD != null){
        StockDiversCrud sdc = new StockDiversCrud();
        sdc.supprimerStockDivers(selectedSD.getIdSD());
        
        sdList.remove(selectedSD);
    }
    }
    
}
