/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.StockDivers;
import entities.Health;
import entities.NomDivStock;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import tools.MyConnection;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author Ahmed Raies
 */
public class StockDiversCrud {
    
    Connection cnx2;
    public StockDiversCrud(){
       cnx2 =MyConnection.getInstance().getCnx();
   }
    
    
     public void ajouterStockDivers(StockDivers sd){
        String sql = "INSERT INTO stdivers (nomSD,quantiteSD,healthSD,dateEntreeStock)"+
            "VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(sql);
            pst.setString(1, sd.getNomSD().toString());
            pst.setFloat(2, sd.getQuantiteSD());
            pst.setString(3, sd.getHealthSD().toString());
            pst.setDate(4, (Date) sd.getDateEntreeStock());
            
            pst.executeUpdate();
            System.out.println("Un autre produit est ajouté avec succès !");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }   
      
    public List<StockDivers> afficherStockDivers(){
    List<StockDivers> myList = new ArrayList<>();
    String sql ="SELECT * FROM stdivers";
        try {
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                StockDivers sd = new StockDivers(); 
                sd.setIdSD(rs.getInt(1));
                sd.setNomSD(NomDivStock.valueOf(rs.getString("nomSD")));
                sd.setQuantiteSD(rs.getFloat("quantiteSD"));
                sd.setHealthSD(Health.valueOf(rs.getString("healthSD")));
                sd.setDateEntreeStock(rs.getDate("dateEntreeStock"));
                myList.add(sd);      
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return myList;
 } 
    
    public StockDivers chercherStockDivers(int id) {
    String sql = "SELECT * FROM stdivers WHERE idSD=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
                StockDivers sd = new StockDivers(); 
                sd.setIdSD(rs.getInt(1));
                sd.setNomSD(NomDivStock.valueOf(rs.getString("nomSD")));
                sd.setQuantiteSD(rs.getFloat("quantiteSD"));
                sd.setHealthSD(Health.valueOf(rs.getString("healthSD")));
                sd.setDateEntreeStock(rs.getDate("dateEntreeStock"));
            return sd;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}
       
    public void modifierStockDivers(StockDivers sd) {
    String sql = "UPDATE stdivers SET nomSD=?, quantiteSD=?,  healthSD=?, dateEntreeStock=? WHERE idSD=?";
    try {
            PreparedStatement pst = cnx2.prepareStatement(sql);
            pst.setString(1, sd.getNomSD().toString());
            pst.setFloat(2, sd.getQuantiteSD());
            pst.setString(3, sd.getHealthSD().toString());
            pst.setDate(4, (Date) sd.getDateEntreeStock());
            pst.setInt(5, sd.getIdSD());
            
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Le produit a été modifié avec succès !");
        } else {
            System.out.println("Aucune modification n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
    public void supprimerStockDivers(int id) {
    String sql = "DELETE FROM stdivers WHERE idSD=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Le produit a été supprimé avec succès !");
        } else {
            System.out.println("Aucune suppression n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
}
