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
public enum NomDivStock {
    Miel,
    Lait,
    Oeufs,
    Cire,
    Laine,
    Peau,
    Fumier,
    Déchet_végétal;

    public static NomDivStock getMiel() {
        return Miel;
    }

    public static NomDivStock getLait() {
        return Lait;
    }

    public static NomDivStock getOeufs() {
        return Oeufs;
    }

    public static NomDivStock getCire() {
        return Cire;
    }

    public static NomDivStock getLaine() {
        return Laine;
    }

    public static NomDivStock getPeau() {
        return Peau;
    }

    public static NomDivStock getFumier() {
        return Fumier;
    }

    public static NomDivStock getDéchet_végétal() {
        return Déchet_végétal;
    }
    
    
}
