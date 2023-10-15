/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Plante;
import entities.Health;
import entities.EtatPlante;
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
public class PlanteCrud {
    
    Connection cnx2;
    public PlanteCrud(){
       cnx2 =MyConnection.getInstance().getCnx();
   }
    
    public void ajouterPlante(Plante p){
        String sql = "INSERT INTO stplante (nomplante,etatplante,healthplante,quantiteplante,dateEntreeStock)"+
            "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(sql);
            pst.setString(1, p.getNomplante());
            pst.setString(2, p.getEtatplante().toString());
            pst.setString(3, p.getHealthplante().toString());
            pst.setFloat(4, p.getQuantiteplante());
            pst.setDate(5, (Date) p.getDateEntreeStock());
            
            pst.executeUpdate();
            System.out.println("La plante est ajoutée avec succès !");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }   
      
    public List<Plante> afficherPlante(){
    List<Plante> myList = new ArrayList<>();
    String sql ="SELECT * FROM stplante";
        try {
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Plante p = new Plante();
                p.setIdplante(rs.getInt(1));
                p.setNomplante(rs.getString("nomplante"));
                p.setEtatplante(EtatPlante.valueOf(rs.getString("etatplante")));
                p.setHealthplante(Health.valueOf(rs.getString("healthplante")));
                p.setQuantiteplante(rs.getFloat("quantiteplante"));
                p.setDateEntreeStock(rs.getDate("dateEntreeStock"));
                myList.add(p);      
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return myList;
 } 
    
    public Plante chercherPlante(int id) {
    String sql = "SELECT * FROM stplante WHERE idplante=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Plante p = new Plante();
                p.setIdplante(rs.getInt(1));
                p.setNomplante(rs.getString("nomplante"));
                p.setEtatplante(EtatPlante.valueOf(rs.getString("etatplante")));
                p.setHealthplante(Health.valueOf(rs.getString("healthplante")));
                p.setQuantiteplante(rs.getFloat("quantiteplante"));
                p.setDateEntreeStock(rs.getDate("dateEntreeStock"));
            return p;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}
       
    public void modifierPlante(Plante p) {
    String sql = "UPDATE stplante SET nomplante=?, etatplante=?, healthplante=?, quantiteplante=?, dateEntreeStock=? WHERE idplante=?";
    try {
            PreparedStatement pst = cnx2.prepareStatement(sql);
            pst.setString(1, p.getNomplante());
            pst.setString(2, p.getEtatplante().toString());
            pst.setString(3, p.getHealthplante().toString());
            pst.setFloat(4, p.getQuantiteplante());
            pst.setDate(5, (Date) p.getDateEntreeStock());
            pst.setInt(6, p.getIdplante());

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("La plante a été modifiée avec succès !");
        } else {
            System.out.println("Aucune modification n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
    public void supprimerPlante(int id) {
    String sql = "DELETE FROM stplante WHERE idplante=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("La plante a été supprimée avec succès !");
        } else {
            System.out.println("Aucune suppression n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

   
    
}
