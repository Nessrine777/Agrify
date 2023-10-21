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
import services.PlanteCrud;  
 
 
/**
 * FXML Controller class
 *
 * @author Ahmed Raies
 */
public class PlanteStockController implements Initializable {

    @FXML
    private Button btmenusta;
    @FXML
    private Button btmenustp;
    @FXML
    private Button btmenustd;
    @FXML
    private TableView<Plante> tablevstp;
    @FXML
    private TableColumn<Plante, Float> colidstp;
    @FXML
    private TableColumn<Plante, String> colnomstp;
    @FXML
    private TableColumn<Plante, EtatPlante> coletatstp;
    @FXML
    private TableColumn<Plante, Health> colhealthstp;
    @FXML
    private TableColumn<Plante, Float> colquantitestp;
    @FXML
    private TableColumn<Plante, Date> coldatestp;
    @FXML
    private TextField tfnomstp;
    @FXML
    private ComboBox<EtatPlante> cbetatstp;
    @FXML
    private ComboBox<Health> cbhealthstp;
    @FXML
    private TextField tfquantitestp;
    @FXML
    private DatePicker datestp;
    @FXML
    private Button btajstp;
    @FXML
    private Button btmodifstp;
    @FXML
    private Button btsuppstp;
    private ObservableList<Plante> planteList = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<EtatPlante> etatplanteList = FXCollections.observableArrayList(EtatPlante.values());
        cbetatstp.setItems(etatplanteList);
        
        ObservableList<Health> healthList = FXCollections.observableArrayList(Health.values());
        cbhealthstp.setItems(healthList);
        
        colidstp.setCellValueFactory(new PropertyValueFactory<>("idplante"));
        colnomstp.setCellValueFactory(new PropertyValueFactory<>("nomplante"));
        coletatstp.setCellValueFactory(new PropertyValueFactory<>("etatplante"));
        colhealthstp.setCellValueFactory(new PropertyValueFactory<>("healthplante"));
        colquantitestp.setCellValueFactory(new PropertyValueFactory<>("quantiteplante"));
        coldatestp.setCellValueFactory(new PropertyValueFactory<>("DateEntreeStock"));
        PlanteCrud pc = new PlanteCrud();
        planteList.addAll(pc.afficherPlante()); 
        tablevstp.setItems(planteList);
        
       //Selection 
        tablevstp.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedPlante) -> {
    if (selectedPlante != null) {
        tfnomstp.setText(selectedPlante.getNomplante());
        cbetatstp.setValue(selectedPlante.getEtatplante());
        cbhealthstp.setValue(selectedPlante.getHealthplante());
        tfquantitestp.setText(String.valueOf(selectedPlante.getQuantiteplante()));
        
        java.util.Date utilDate = selectedPlante.getDateEntreeStock();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        datestp.setValue(sqlDate.toLocalDate());  
    }
});
      
        
    }    

    @FXML
    private void addstp(ActionEvent event) {
        
    String nomplante = tfnomstp.getText();
    EtatPlante etatplante = cbetatstp.getValue();
    Health healthplante= cbhealthstp.getValue();
    float quantiteplante  = Float.parseFloat(tfquantitestp.getText());
    LocalDate localDate = datestp.getValue();
    Date DateEntreeStock = Date.valueOf(localDate);

        
    Plante p = new Plante(nomplante, etatplante, healthplante, quantiteplante, DateEntreeStock);    
    PlanteCrud  pc = new PlanteCrud ();
    pc.ajouterPlante(p);
    
    planteList.add(p);
    
    tablevstp.setItems(planteList);
    }

    @FXML
    private void modifstp(ActionEvent event) {
        
         Plante selectedPlante = tablevstp.getSelectionModel().getSelectedItem();

      if (selectedPlante != null) {
        // Récupérez les nouvelles valeurs des champs du formulaire
        String newNomPlante = tfnomstp.getText();
        EtatPlante newEtatPlante = cbetatstp.getValue();
        Health newHealthPlante = cbhealthstp.getValue();
        float newQuantitePlante = Float.parseFloat(tfquantitestp.getText());
        LocalDate localDate = datestp.getValue();
        Date newDateEntreeStock = Date.valueOf(localDate);

        // Mettez à jour les propriétés de l'objet sélectionné
        selectedPlante.setNomplante(newNomPlante);
        selectedPlante.setEtatplante(newEtatPlante);
        selectedPlante.setHealthplante(newHealthPlante);
        selectedPlante.setQuantiteplante(newQuantitePlante);
        selectedPlante.setDateEntreeStock(newDateEntreeStock);

        // Mettez à jour la ligne sélectionnée dans la TableView
        int selectedIndex = tablevstp.getSelectionModel().getSelectedIndex();
        planteList.set(selectedIndex, selectedPlante);
        
        PlanteCrud pc = new PlanteCrud();
        pc.modifierPlante(selectedPlante);

        // Réinitialisez les champs du formulaire
        tfnomstp.clear();
        cbetatstp.getSelectionModel().clearSelection();
        cbhealthstp.getSelectionModel().clearSelection();
        tfquantitestp.clear();
        datestp.getEditor().clear();
    }

       
    }

    @FXML
    private void suppstp(ActionEvent event) {
        
    Plante selectedPlante = tablevstp.getSelectionModel().getSelectedItem();

      if (selectedPlante != null){
        PlanteCrud pc = new PlanteCrud();
        pc.supprimerPlante(selectedPlante.getIdplante());
        
        planteList.remove(selectedPlante);
    }
    }
   @FXML
    private void fnsta(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimalStock.fxml"));
        Parent root = loader.load();

        Scene AnimalStockScene = new Scene(root);

        Stage stage = (Stage) btmenusta.getScene().getWindow();

        stage.setScene(AnimalStockScene);
    } catch (IOException e) {
              
    }
    }

    @FXML
    private void fnstp(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanteStock.fxml"));
        Parent root = loader.load();

        Scene PlanteStockScene = new Scene(root);

        Stage stage = (Stage) btmenustp.getScene().getWindow();

        stage.setScene(PlanteStockScene);
    } catch (IOException e) {
             
    }
    }

    @FXML
    private void fnstd(ActionEvent event) {
              try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StockDivers.fxml"));
        Parent root = loader.load();

        Scene StockDiversScene = new Scene(root);

        Stage stage = (Stage) btmenustd.getScene().getWindow();

        stage.setScene(StockDiversScene);
    } catch (IOException e) {  
             
    }
    }
    
}
