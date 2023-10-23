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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private Button btmenustd;
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
    @FXML
    private Label lbmstd;
    @FXML
    private Button btexpstd;
    @FXML
    private Button bttachestd;
    @FXML
    private Button btrecstd;
    @FXML
    private LineChart<String, Number> linechartestd;
    @FXML
    private TextField tfrechstd;


    /**
     * Initializes the controller class.
     */
    
    
        private void updateLineChartstd() {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("StockDivers par date");

    // Créez un dictionnaire pour stocker le nombre d'éléments par date
    Map<String, Integer> stockParDate = new HashMap<>();

    // Parcourez les éléments de votre TableView et comptez le nombre d'éléments par date
    for (StockDivers std : tablevsd.getItems()) {
        String date = std.getDateEntreeStock().toString();

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

    linechartestd.getData().clear();
    linechartestd.getData().add(series);
        }
        
        
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
      updateLineChartstd();
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
        NomDivStock newNomSD = cbnomsd.getValue();
        Health newHealthSD = cbhealthsd.getValue();
        float newQuantiteSD = Float.parseFloat(tfquantitesd.getText());
        LocalDate localDate = datesd.getValue();
        Date newDateEntreeStock = Date.valueOf(localDate);

        selectedSD.setNomSD(newNomSD);
        selectedSD.setHealthSD(newHealthSD);
        selectedSD.setQuantiteSD(newQuantiteSD);
        selectedSD.setDateEntreeStock(newDateEntreeStock);

        int selectedIndex = tablevsd.getSelectionModel().getSelectedIndex();
        sdList.set(selectedIndex, selectedSD);
        
        StockDiversCrud sdc = new StockDiversCrud();
        sdc.modifierStockDivers(selectedSD);

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
       cbnomsd.getSelectionModel().clearSelection();
        cbhealthsd.getSelectionModel().clearSelection();
        tfquantitesd.clear();
        datesd.getEditor().clear();
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
    private void exportstd(ActionEvent event) {
         try {
        String outputFile = "stockDivers_data.pdf";

        // Créez un document PDF
        Document document = new Document();

        // Créez un écrivain PDF en spécifiant le fichier de sortie
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));

        // Ouvrez le document
        document.open();

        // Ajoutez un titre
        Paragraph title = new Paragraph("Données du Stock Divers");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Créez une table pour afficher les données
        PdfPTable table = new PdfPTable(5); // 5 colonnes pour vos données
        table.setWidthPercentage(100);

        // En-têtes de colonne
        table.addCell("ID");
        table.addCell("Nom");
        table.addCell("Santé");
        table.addCell("Quantité");
        table.addCell("Date d'entrée");

        // Remplissez la table avec les données du stock divers
        for (StockDivers sd : sdList) {
            table.addCell(String.valueOf(sd.getIdSD()));
            table.addCell(sd.getNomSD().toString());
            table.addCell(sd.getHealthSD().toString());
            table.addCell(String.valueOf(sd.getQuantiteSD()));
            table.addCell(sd.getDateEntreeStock().toString());
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
    private void tachestd(ActionEvent event) {
    }

    @FXML
    private void recstd(ActionEvent event) {
    }

    @FXML
    private void rechercherstd(ActionEvent event) {
        String termeRecherche = tfrechstd.getText().toLowerCase();
    ObservableList<StockDivers> resultatsRecherche = FXCollections.observableArrayList();

    if (termeRecherche.isEmpty()) {
        // Si le champ de recherche est vide, affichez tous les éléments
        tablevsd.setItems(sdList);
    } else {
        // Sinon, effectuez la recherche
        for (StockDivers std : sdList) {
            if (std.getNomSD().toString().toLowerCase().contains(termeRecherche)) {
                resultatsRecherche.add(std);
            }
        }

        tablevsd.setItems(resultatsRecherche);
    }
    }
}
