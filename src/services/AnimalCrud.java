/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Animal;
import entities.Health;
import entities.SexeAnimal;
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
public class AnimalCrud {
    
    Connection cnx2;
    public AnimalCrud(){
       cnx2 =MyConnection.getInstance().getCnx();
   }
    
    public void ajouterAnimal(Animal a){
        String sql = "INSERT INTO stanimal (nomanimal,sexeanimal,ageanimal,poidsanimal,healthanimal,dateEntreeStock)"+
            "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(sql);
            pst.setString(1, a.getNomanimal());
            pst.setString(2, a.getSexeanimal().toString());
            pst.setInt(3, a.getAgeanimal());
            pst.setFloat(4, a.getPoidsanimal());
            pst.setString(5, a.getHealthanimal().toString());
            pst.setDate(6, (Date) a.getDateEntreeStock());
            
            pst.executeUpdate();
            System.out.println("L'animal est ajouté avec succès !");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }   
      
    public List<Animal> afficherAnimal(){
    List<Animal> myList = new ArrayList<>();
    String sql ="SELECT * FROM stanimal";
        try {
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Animal a = new Animal();
                a.setIdanimal(rs.getInt(1));
                a.setNomanimal(rs.getString("nomanimal"));
                a.setSexeanimal(SexeAnimal.valueOf(rs.getString("sexeanimal")));
                a.setAgeanimal(rs.getInt("ageanimal"));
                a.setPoidsanimal(rs.getFloat("poidsanimal"));
                a.setHealthanimal(Health.valueOf(rs.getString("healthanimal")));
                a.setDateEntreeStock(rs.getDate("dateEntreeStock"));
                myList.add(a);      
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return myList;
 } 
    
    public Animal chercherAnimal(int id) {
    String sql = "SELECT * FROM stanimal WHERE idanimal=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Animal a = new Animal();
            a.setIdanimal(rs.getInt("idanimal"));
            a.setNomanimal(rs.getString("nomanimal"));
            a.setSexeanimal(SexeAnimal.valueOf(rs.getString("sexeanimal")));
            a.setAgeanimal(rs.getInt("ageanimal"));
            a.setPoidsanimal(rs.getFloat("poidsanimal"));
            a.setHealthanimal(Health.valueOf(rs.getString("healthanimal")));
            a.setDateEntreeStock(rs.getDate("dateEntreeStock"));
            return a;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null; // Renvoie null si l'animal n'est pas trouvé
}
       
    public void modifierAnimal(Animal a) {
    String sql = "UPDATE stanimal SET nomanimal=?, sexeanimal=?, ageanimal=?, poidsanimal=?, healthanimal=?, dateEntreeStock=? WHERE idanimal=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setString(1, a.getNomanimal());
        pst.setString(2, a.getSexeanimal().toString());
        pst.setInt(3, a.getAgeanimal());
        pst.setFloat(4, a.getPoidsanimal());
        pst.setString(5, a.getHealthanimal().toString());
        pst.setDate(6, (Date) a.getDateEntreeStock());
        pst.setInt(7, a.getIdanimal());

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("L'animal a été modifié avec succès !");
        } else {
            System.out.println("Aucune modification n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
    public void supprimerAnimal(int id) {
    String sql = "DELETE FROM stanimal WHERE idanimal=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("L'animal a été supprimé avec succès !");
        } else {
            System.out.println("Aucune suppression n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    public int getGeneratedAnimalId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}





