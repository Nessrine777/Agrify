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
import javafx.scene.control.TextField;
import entities.*;
import java.time.LocalDate;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.AnimalCrud;



/**
 * FXML Controller class
 *
 * @author Ahmed Raies
 */
public class AnimalStockController implements Initializable {

    @FXML
    private TextField tfnomsta;
    @FXML
    private ComboBox<SexeAnimal> cbsexesta;
    @FXML
    private TextField tfagesta;
    @FXML
    private TextField tfpoidssta;
    @FXML
    private ComboBox<Health> cbhealthsta;
    @FXML
    private DatePicker tfdatesta;
    @FXML
    private Button btajsta;
    @FXML
    private Button btmodifsta;
    @FXML
    private Button btsuppsta;
    @FXML
    private TableView<Animal> tablevsta;
    @FXML
    private TableColumn<Animal, Integer> colidsta;
    @FXML
    private TableColumn<Animal, String> colnomsta;
    @FXML
    private TableColumn<Animal, SexeAnimal> colsexesta;
    @FXML
    private TableColumn<Animal, Integer> colagesta;
    @FXML
    private TableColumn<Animal, Float> colpoidssta;
    @FXML
    private TableColumn<Animal, Health> colhealthsta;
    @FXML
    private TableColumn<Animal, Date> coldatesta;
    private ObservableList<Animal> animalList = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
         // Remplissez le ComboBox pour SexeAnimal
        ObservableList<SexeAnimal> sexeList = FXCollections.observableArrayList(SexeAnimal.values());
        cbsexesta.setItems(sexeList);
        
        // Remplissez le ComboBox pour Health
        ObservableList<Health> healthList = FXCollections.observableArrayList(Health.values());
        cbhealthsta.setItems(healthList);
        
        colidsta.setCellValueFactory(new PropertyValueFactory<>("idanimal"));
        colnomsta.setCellValueFactory(new PropertyValueFactory<>("nomanimal"));
        colsexesta.setCellValueFactory(new PropertyValueFactory<>("sexeanimal"));
        colagesta.setCellValueFactory(new PropertyValueFactory<>("ageanimal"));
        colpoidssta.setCellValueFactory(new PropertyValueFactory<>("poidsanimal"));
        colhealthsta.setCellValueFactory(new PropertyValueFactory<>("healthanimal"));
        coldatesta.setCellValueFactory(new PropertyValueFactory<>("DateEntreeStock"));
        AnimalCrud ac = new AnimalCrud();
        animalList.addAll(ac.afficherAnimal()); 
        tablevsta.setItems(animalList);
        
       //Selection 
        tablevsta.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedAnimal) -> {
    if (selectedAnimal != null) {
        tfnomsta.setText(selectedAnimal.getNomanimal());
        cbsexesta.setValue(selectedAnimal.getSexeanimal());
        tfagesta.setText(String.valueOf(selectedAnimal.getAgeanimal()));
        tfpoidssta.setText(String.valueOf(selectedAnimal.getPoidsanimal()));
        cbhealthsta.setValue(selectedAnimal.getHealthanimal());
        
        java.util.Date utilDate = selectedAnimal.getDateEntreeStock();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        tfdatesta.setValue(sqlDate.toLocalDate());  
    }
});
      
        
    }    

    @FXML
    private void addsta(ActionEvent event) {
        
    String nomanimal = tfnomsta.getText();
    SexeAnimal sexeanimal = cbsexesta.getValue(); 
    int ageanimal = Integer.parseInt(tfagesta.getText());
    float poidsanimal = Float.parseFloat(tfpoidssta.getText());
    Health healthanimal= cbhealthsta.getValue();
       // Date DateEntreeStock
   LocalDate localDate = tfdatesta.getValue();
    Date DateEntreeStock = Date.valueOf(localDate);

        
    Animal a = new Animal(nomanimal, sexeanimal, ageanimal, poidsanimal, healthanimal, DateEntreeStock);    
    AnimalCrud  ac = new AnimalCrud ();
    ac.ajouterAnimal(a);
    
    // Ajoutez le nouvel animal à la liste
    animalList.add(a);
    
    // Rafraîchissez la TableView
    tablevsta.setItems(animalList);
        
    }

    @FXML
    private void modifsta(ActionEvent event) {
         
      Animal selectedAnimal = tablevsta.getSelectionModel().getSelectedItem();

    if (selectedAnimal != null) {
        // Récupérez les nouvelles valeurs des champs du formulaire
        String newNomAnimal = tfnomsta.getText();
        SexeAnimal newSexeAnimal = cbsexesta.getValue();
        int newAgeAnimal = Integer.parseInt(tfagesta.getText());
        float newPoidsAnimal = Float.parseFloat(tfpoidssta.getText());
        Health newHealthAnimal = cbhealthsta.getValue();
        LocalDate localDate = tfdatesta.getValue();
        Date newDateEntreeStock = Date.valueOf(localDate);

        // Mettez à jour les propriétés de l'objet Animal sélectionné
        selectedAnimal.setNomanimal(newNomAnimal);
        selectedAnimal.setSexeanimal(newSexeAnimal);
        selectedAnimal.setAgeanimal(newAgeAnimal);
        selectedAnimal.setPoidsanimal(newPoidsAnimal);
        selectedAnimal.setHealthanimal(newHealthAnimal);
        selectedAnimal.setDateEntreeStock(newDateEntreeStock);

        // Mettez à jour la ligne sélectionnée dans la TableView
        int selectedIndex = tablevsta.getSelectionModel().getSelectedIndex();
        animalList.set(selectedIndex, selectedAnimal);
        
        AnimalCrud ac = new AnimalCrud();
        ac.modifierAnimal(selectedAnimal);

        // Réinitialisez les champs du formulaire
        tfnomsta.clear();
        cbsexesta.getSelectionModel().clearSelection();
        tfagesta.clear();
        tfpoidssta.clear();
        cbhealthsta.getSelectionModel().clearSelection();
        tfdatesta.getEditor().clear();
    }

       
    }

    @FXML
    private void suppsta(ActionEvent event) {
    
      Animal selectedAnimal = tablevsta.getSelectionModel().getSelectedItem();

      if (selectedAnimal != null){
        AnimalCrud animalCrud = new AnimalCrud();
        animalCrud.supprimerAnimal(selectedAnimal.getIdanimal());
        
        animalList.remove(selectedAnimal);
    }
  }
}
