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
public class Animal {
    
    private int idanimal;
    private String nomanimal;
    private SexeAnimal sexeanimal;
    private int ageanimal;
    private float poidsanimal;
    private Health healthanimal;
    private Date DateEntreeStock;

    public Animal() {
    }

    public Animal(String nomanimal, SexeAnimal sexeanimal, int ageanimal, float poidsanimal, Health healthanimal, Date DateEntreeStock) {
        this.nomanimal = nomanimal;
        this.sexeanimal = sexeanimal;
        this.ageanimal = ageanimal;
        this.poidsanimal = poidsanimal;
        this.healthanimal = healthanimal;
        this.DateEntreeStock = DateEntreeStock;
    }

    public Animal(int idanimal, String nomanimal, SexeAnimal sexeanimal, int ageanimal, float poidsanimal, Health healthanimal, Date DateEntreeStock) {
        this.idanimal = idanimal;
        this.nomanimal = nomanimal;
        this.sexeanimal = sexeanimal;
        this.ageanimal = ageanimal;
        this.poidsanimal = poidsanimal;
        this.healthanimal = healthanimal;
        this.DateEntreeStock = DateEntreeStock;
    }

    public int getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
    }

    public String getNomanimal() {
        return nomanimal;
    }

    public void setNomanimal(String nomanimal) {
        this.nomanimal = nomanimal;
    }

    public SexeAnimal getSexeanimal() {
        return sexeanimal;
    }

    public void setSexeanimal(SexeAnimal sexeanimal) {
        this.sexeanimal = sexeanimal;
    }

    public int getAgeanimal() {
        return ageanimal;
    }

    public void setAgeanimal(int ageanimal) {
        this.ageanimal = ageanimal;
    }

    public float getPoidsanimal() {
        return poidsanimal;
    }

    public void setPoidsanimal(float poidsanimal) {
        this.poidsanimal = poidsanimal;
    }

    public Health getHealthanimal() {
        return healthanimal;
    }

    public void setHealthanimal(Health healthanimal) {
        this.healthanimal = healthanimal;
    }

    public Date getDateEntreeStock() {
        return DateEntreeStock;
    }

    public void setDateEntreeStock(Date DateEntreeStock) {
        this.DateEntreeStock = DateEntreeStock;
    }

    @Override
    public String toString() {
        return "Animal{" + "idanimal=" + idanimal + ", nomanimal=" + nomanimal + ", sexeanimal=" + sexeanimal + ", ageanimal=" + ageanimal + ", poidsanimal=" + poidsanimal + ", healthanimal=" + healthanimal + ", DateEntreeStock=" + DateEntreeStock + '}';
    }
    
    
    
}
