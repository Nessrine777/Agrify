/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Ahmed Raies
 */
public enum EtatPlante {
    Fruit,
    Légume,
    Racines,
    Grains,
    Fleur,
    Arbre,
    Feuillage ,
    Extrait_huileux;
    
    public static EtatPlante getFruit() {
        return Fruit;
    }

    public static EtatPlante getLégume() {
        return Légume;
    }

    public static EtatPlante getRacines() {
        return Racines;
    }

    public static EtatPlante getGrains() {
        return Grains;
    }

    public static EtatPlante getFleur() {
        return Fleur;
    }

    public static EtatPlante getArbre() {
        return Arbre;
    }

    public static EtatPlante getFeuillage() {
        return Feuillage;
    }

    public static EtatPlante getExtrait_huileux() {
        return Extrait_huileux;
    }

    
}
