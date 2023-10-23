/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stgui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Alert.AlertType;
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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.AnimalCrud;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;



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
    @FXML
    private Button btmenusta;
    @FXML
    private Button btmenustp;
    @FXML
    private Button btmenustd;
    @FXML
    private Label lbmsta;
    @FXML
    private Button btexpsta;
    @FXML
    private Button bttachesta;
    @FXML
    private Button btrecsta;
    @FXML
    private LineChart<String, Number> linechartsta; 
    @FXML
    private TextField tfrechsta;


    /**
     * Initializes the controller class.
     */
    
    
    private void updateLineChart() {
   XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Stock animal par date");

    // Créez un dictionnaire pour stocker le nombre d'éléments par date
    Map<String, Integer> stockParDate = new HashMap<>();

    // Parcourez les éléments de votre TableView et comptez le nombre d'éléments par date
    for (Animal animal : tablevsta.getItems()) {
        String date = animal.getDateEntreeStock().toString();

        if (stockParDate.containsKey(date)) {
            stockParDate.put(date, stockParDate.get(date) + 1);
        } else {
            stockParDate.put(date, 1);
        }
    }

    // Parcourez le dictionnaire et ajoutez les données au graphique
    for (Map.Entry<String, Integer> entry : stockParDate.entrySet()) {
        String date = entry.getKey();
        int stock = entry.getValue();
        series.getData().add(new XYChart.Data<>(date, stock));
    }

    linechartsta.getData().clear();
    linechartsta.getData().add(series);
}

    
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
    updateLineChart();    
    }   
    private void showAlert(String message) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Erreur de saisie");
          alert.setHeaderText(null);
          alert.setContentText(message);
          alert.showAndWait();
}

    @FXML
    private void addsta(ActionEvent event) {
        
   String nomanimal = tfnomsta.getText();
    SexeAnimal sexeanimal = cbsexesta.getValue(); 
    int ageanimal = Integer.parseInt(tfagesta.getText());
    float poidsanimal = Float.parseFloat(tfpoidssta.getText());
    Health healthanimal= cbhealthsta.getValue();
    LocalDate localDate = tfdatesta.getValue();
    Date DateEntreeStock = Date.valueOf(localDate);
    
     if (nomanimal.isEmpty() || sexeanimal == null || tfagesta.getText().isEmpty() || tfpoidssta.getText().isEmpty() || healthanimal == null || localDate == null) {
    
        showAlert("Veuillez remplir tous les champs du formulaire.");
        return;
   
    }

        
    Animal a = new Animal(nomanimal, sexeanimal, ageanimal, poidsanimal, healthanimal, DateEntreeStock);    
    AnimalCrud  ac = new AnimalCrud ();
    ac.ajouterAnimal(a);
        animalList.add(a);
        tablevsta.setItems(animalList);        
        tfnomsta.clear();
        cbsexesta.getSelectionModel().clearSelection();
        tfagesta.clear();
        tfpoidssta.clear();
        cbhealthsta.getSelectionModel().clearSelection();
        tfdatesta.getEditor().clear();
        
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
       tfnomsta.clear();
        cbsexesta.getSelectionModel().clearSelection();
        tfagesta.clear();
        tfpoidssta.clear();
        cbhealthsta.getSelectionModel().clearSelection();
        tfdatesta.getEditor().clear();
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

    
    
    
    
    @FXML
    private void exportsta(ActionEvent event) {
               try {
        String outputFile = "stockAnimal_data.pdf";

        // Créez un document PDF
        Document document = new Document();

        // Créez un écrivain PDF en spécifiant le fichier de sortie
        PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream(outputFile));

        // Ouvrez le document
        document.open();

        // Ajoutez un titre
        Paragraph title = new Paragraph("Données du Stock Animal");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Créez une table pour afficher les données
        PdfPTable table = new PdfPTable(7); // 7 colonnes pour vos données
        table.setWidthPercentage(100);

        // En-têtes de colonne
        table.addCell("ID");
        table.addCell("Nom");
        table.addCell("Sexe");
        table.addCell("Âge");
        table.addCell("Poids");
        table.addCell("Santé");
        table.addCell("Date d'entrée");

        // Remplissez la table avec les données des animaux
        for (Animal animal : animalList) {
            table.addCell(String.valueOf(animal.getIdanimal()));
            table.addCell(animal.getNomanimal());
            table.addCell(animal.getSexeanimal().toString());
            table.addCell(String.valueOf(animal.getAgeanimal()));
            table.addCell(String.valueOf(animal.getPoidsanimal()));
            table.addCell(animal.getHealthanimal().toString());
            table.addCell(animal.getDateEntreeStock().toString());
        }

        // Ajoutez la table au document
        document.add(table);

        // Fermez le document
        document.close();
         // Affichez une alerte de succès
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Le fichier PDF a été exporté avec succès !");
        alert.showAndWait();
    } catch (DocumentException | IOException e) {
        e.printStackTrace();

        // En cas d'erreur, affichez une alerte d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur lors de l'exportation du PDF");
        alert.setContentText("Une erreur s'est produite lors de l'exportation du fichier PDF.");
        alert.showAndWait();
    }
   
    }

    @FXML
    private void tachesta(ActionEvent event) {
    }

    @FXML
    private void recsta(ActionEvent event) {
    }

    @FXML
    private void recherchersta(ActionEvent event) {
        String termeRecherche = tfrechsta.getText().toLowerCase();
    ObservableList<Animal> resultatsRecherche = FXCollections.observableArrayList();

    if (termeRecherche.isEmpty()) {
        // Si le champ de recherche est vide, affichez tous les éléments
        tablevsta.setItems(animalList);
    } else {
        // Sinon, effectuez la recherche
        for (Animal animal : animalList) {
            if (animal.getNomanimal().toLowerCase().contains(termeRecherche)) {
                resultatsRecherche.add(animal);
            }
        }

        tablevsta.setItems(resultatsRecherche);
    }
    }
}
