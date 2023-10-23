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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import entities.*; 
import java.io.FileOutputStream;
import java.io.IOException; 
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    @FXML
    private Label lbmstp;
    @FXML
    private Button btexpstp;
    @FXML
    private Button bttachestp;
    @FXML
    private Button btrecstp;
    @FXML
    private LineChart<String, Number> linechartestp;
    @FXML
    private TextField tfrechstp;


    /**
     * Initializes the controller class.
     */
    
    private void updateLineChartstp() {
     XYChart.Series<String, Number> series = new XYChart.Series<>();
     series.setName("Stock plante par date");

    // Créez un dictionnaire pour stocker le nombre d'éléments par date
    Map<String, Integer> stockParDate = new HashMap<>();

    // Parcourez les éléments de votre TableView et comptez le nombre d'éléments par date
    for (Plante plante : tablevstp.getItems()) {
        String date = plante.getDateEntreeStock().toString();

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

    linechartestp.getData().clear();
    linechartestp.getData().add(series);
}
    
    
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
     updateLineChartstp();
        
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
        String newNomPlante = tfnomstp.getText();
        EtatPlante newEtatPlante = cbetatstp.getValue();
        Health newHealthPlante = cbhealthstp.getValue();
        float newQuantitePlante = Float.parseFloat(tfquantitestp.getText());
        LocalDate localDate = datestp.getValue();
        Date newDateEntreeStock = Date.valueOf(localDate);

        selectedPlante.setNomplante(newNomPlante);
        selectedPlante.setEtatplante(newEtatPlante);
        selectedPlante.setHealthplante(newHealthPlante);
        selectedPlante.setQuantiteplante(newQuantitePlante);
        selectedPlante.setDateEntreeStock(newDateEntreeStock);

        int selectedIndex = tablevstp.getSelectionModel().getSelectedIndex();
        planteList.set(selectedIndex, selectedPlante);
        
        PlanteCrud pc = new PlanteCrud();
        pc.modifierPlante(selectedPlante);

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
         tfnomstp.clear();
        cbetatstp.getSelectionModel().clearSelection();
        cbhealthstp.getSelectionModel().clearSelection();
        tfquantitestp.clear();
        datestp.getEditor().clear();
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
    private void exportstp(ActionEvent event) {
        
         try {
        String outputFile = "stockPlante_data.pdf";

        // Créez un document PDF
        Document document = new Document();

        // Créez un écrivain PDF en spécifiant le fichier de sortie
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));

        // Ouvrez le document
        document.open();

        // Ajoutez un titre
        Paragraph title = new Paragraph("Données du Stock de Plantes");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Créez une table pour afficher les données
        PdfPTable table = new PdfPTable(6); // 6 colonnes pour vos données
        table.setWidthPercentage(100);

        // En-têtes de colonne
        table.addCell("ID");
        table.addCell("Nom");
        table.addCell("État");
        table.addCell("Santé");
        table.addCell("Quantité");
        table.addCell("Date d'entrée");

        // Remplissez la table avec les données des plantes
        for (Plante plante : planteList) {
            table.addCell(String.valueOf(plante.getIdplante()));
            table.addCell(plante.getNomplante());
            table.addCell(plante.getEtatplante().toString());
            table.addCell(plante.getHealthplante().toString());
            table.addCell(String.valueOf(plante.getQuantiteplante()));
            table.addCell(plante.getDateEntreeStock().toString());
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
    private void tachestp(ActionEvent event) {
    }

    @FXML
    private void recstp(ActionEvent event) {
    }

    @FXML
    private void rechercherstp(ActionEvent event) {
        String termeRecherche = tfrechstp.getText().toLowerCase();
      ObservableList<Plante> resultatsRecherche = FXCollections.observableArrayList();

    if (termeRecherche.isEmpty()) {
        // Si le champ de recherche est vide, affichez tous les éléments
        tablevstp.setItems(planteList);
    } else {
        // Sinon, effectuez la recherche
        for (Plante plante : planteList) {
            if (plante.getNomplante().toLowerCase().contains(termeRecherche)) {
                resultatsRecherche.add(plante);
            }
        }

        tablevstp.setItems(resultatsRecherche);
    }
    }
    
}
