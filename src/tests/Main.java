/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import entities.Animal;
import static entities.EtatPlante.*;
import entities.StockDivers;
import entities.Plante;
import entities.Health.*;
import static entities.Health.getMalsain;
import static entities.Health.getSain;
import static entities.SexeAnimal.getF;
import static entities.SexeAnimal.getM;
import java.sql.Date;
import tools.MyConnection;
import services.AnimalCrud;
import services.PlanteCrud;
import services.StockDiversCrud;
import entities.*;
import static entities.NomDivStock.*;

/**
 *
 * @author Ahmed Raies
 */
public class Main {
    public static void main(String[] args) {

        //MyConnection mc = new MyConnection();
        AnimalCrud  ac = new AnimalCrud ();
        Animal a = new Animal();
        //AjouterAnimal
        a.setNomanimal("Cheval");
        a.setSexeanimal(getM()); 
        a.setAgeanimal(3);
        a.setPoidsanimal(450.5f);
        a.setHealthanimal(getSain()); 
        a.setDateEntreeStock(new Date(13-10-2023));
        ac.ajouterAnimal(a);
      
        //ModifierAnimal
        a = ac.chercherAnimal(1); 
          if (a != null) {
                a.setNomanimal("Coq"); 
                a.setSexeanimal(getM()); 
                a.setAgeanimal(1);
                a.setPoidsanimal(15.5f);
                a.setHealthanimal(getSain()); 
                a.setDateEntreeStock(new Date(13-10-2023));
                ac.modifierAnimal(a);
        }

        // SupprimerAnimal
       /* int idASupprimer = 5;  
        ac.supprimerAnimal(idASupprimer);*/
    
        
       //AfficherAniaml
       System.out.println(ac.afficherAnimal()); 
       
       
        PlanteCrud  pc = new PlanteCrud ();
        Plante p = new Plante();
        //AjouterPlante
      /* p.setNomplante("Orange");
        p.setEtatplante(getArbre()); 
        p.setHealthplante(getSain());
        p.setQuantiteplante(200.5f);
        p.setDateEntreeStock(new Date(13-10-2022));
        pc.ajouterPlante(p);*/
       
        // ModifierPlante
        /*p = pc.chercherPlante(4); 
          if (p != null) {
                p.setNomplante("Sapin");
                p.setEtatplante(getArbre()); 
                p.setHealthplante(getSain());
                p.setQuantiteplante(200.0f);
                p.setDateEntreeStock(new Date(13-10-2022));
                pc.modifierPlante(p);
        }*/

        // SupprimerPlante
       /* int idpASupprimer = 5;  
        pc.supprimerPlante(idpASupprimer);*/
      
      //AfficherPlante
     System.out.println(pc.afficherPlante()); 
     
     
        StockDiversCrud  sdc = new StockDiversCrud ();
        StockDivers sd = new StockDivers();
        
        //AjouterStockDivers
        sd.setNomSD(getOeufs());
        sd.setQuantiteSD(205f);
        sd.setHealthSD(getSain());
        sd.setDateEntreeStock(new Date(13-10-2022));
        sdc.ajouterStockDivers(sd);
       
        // ModifierStockDivers
       /* sd = sdc.chercherStockDivers(2); 
          if (sd != null) {
                sd.setNomSD(getLait());
                sd.setQuantiteSD(500f);
                sd.setHealthSD(getSain());
                sd.setDateEntreeStock(new Date(06-10-2022));
                sdc.modifierStockDivers(sd);
        }*/

        // SupprimerStockDivers
       /* int idsdASupprimer = 4;  
        sdc.supprimerStockDivers(idsdASupprimer);*/
      
      //AfficherStockDivers
       System.out.println(sdc.afficherStockDivers()); 
        }

   
    
}
