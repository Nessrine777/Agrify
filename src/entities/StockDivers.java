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
public class StockDivers {
    private int idSD;
    private NomDivStock nomSD;
    private float quantiteSD;
    private Health healthSD;
    private Date DateEntreeStock;

    public StockDivers() {
    }

    public StockDivers(NomDivStock nomSD, float quantiteSD, Health healthSD, Date DateEntreeStock) {
        this.nomSD = nomSD;
        this.quantiteSD = quantiteSD;
        this.healthSD = healthSD;
        this.DateEntreeStock = DateEntreeStock;
    }

    public StockDivers(int idSD, NomDivStock nomSD, float quantiteSD, Health healthSD, Date DateEntreeStock) {
        this.idSD = idSD;
        this.nomSD = nomSD;
        this.quantiteSD = quantiteSD;
        this.healthSD = healthSD;
        this.DateEntreeStock = DateEntreeStock;
    }

    public int getIdSD() {
        return idSD;
    }

    public void setIdSD(int idSD) {
        this.idSD = idSD;
    }

    public NomDivStock getNomSD() {
        return nomSD;
    }

    public void setNomSD(NomDivStock nomSD) {
        this.nomSD = nomSD;
    }

    public float getQuantiteSD() {
        return quantiteSD;
    }

    public void setQuantiteSD(float quantiteSD) {
        this.quantiteSD = quantiteSD;
    }

    public Health getHealthSD() {
        return healthSD;
    }

    public void setHealthSD(Health healthSD) {
        this.healthSD = healthSD;
    }

    public Date getDateEntreeStock() {
        return DateEntreeStock;
    }

    public void setDateEntreeStock(Date DateEntreeStock) {
        this.DateEntreeStock = DateEntreeStock;
    }

    @Override
    public String toString() {
        return "StockDivers{" + "idSD=" + idSD + ", nomSD=" + nomSD + ", quantiteSD=" + quantiteSD + ", healthSD=" + healthSD + ", DateEntreeStock=" + DateEntreeStock + '}';
    }
    
    
    
}
