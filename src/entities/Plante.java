/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Ahmed Raies
 */
public class Plante {
    private int idplante;
    private String nomplante;
    private EtatPlante etatplante;
    private Health healthplante;
    private float quantiteplante;
    private Date DateEntreeStock;

    public Plante() {
    }

    public Plante(String nomplante, EtatPlante etatplante, Health healthplante, float quantiteplante, Date DateEntreeStock) {
        this.nomplante = nomplante;
        this.etatplante = etatplante;
        this.healthplante = healthplante;
        this.quantiteplante = quantiteplante;
        this.DateEntreeStock = DateEntreeStock;
    }

    public Plante(int idplante, String nomplante, EtatPlante etatplante, Health healthplante, float quantiteplante, Date DateEntreeStock) {
        this.idplante = idplante;
        this.nomplante = nomplante;
        this.etatplante = etatplante;
        this.healthplante = healthplante;
        this.quantiteplante = quantiteplante;
        this.DateEntreeStock = DateEntreeStock;
    }

    public int getIdplante() {
        return idplante;
    }

    public void setIdplante(int idplante) {
        this.idplante = idplante;
    }

    public String getNomplante() {
        return nomplante;
    }

    public void setNomplante(String nomplante) {
        this.nomplante = nomplante;
    }

    public EtatPlante getEtatplante() {
        return etatplante;
    }

    public void setEtatplante(EtatPlante etatplante) {
        this.etatplante = etatplante;
    }

    public Health getHealthplante() {
        return healthplante;
    }

    public void setHealthplante(Health healthplante) {
        this.healthplante = healthplante;
    }

    public float getQuantiteplante() {
        return quantiteplante;
    }

    public void setQuantiteplante(float quantiteplante) {
        this.quantiteplante = quantiteplante;
    }

    public Date getDateEntreeStock() {
        return DateEntreeStock;
    }

    public void setDateEntreeStock(Date DateEntreeStock) {
        this.DateEntreeStock = DateEntreeStock;
    }

    @Override
    public String toString() {
        return "Plante{" + "idplante=" + idplante + ", nomplante=" + nomplante + ", etatplante=" + etatplante + ", healthplante=" + healthplante + ", quantiteplante=" + quantiteplante + ", DateEntreeStock=" + DateEntreeStock + '}';
    }

   
    
    
    
}
