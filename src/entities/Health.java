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
public enum Health {
    Sain,
    Malsain;

    public static Health getSain() {
        return Sain;
    }

    public static Health getMalsain() {
        return Malsain;
    }
}
