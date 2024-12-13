/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;

/**
 * Cellule Demineur
 *
 * @author danie
 */
public class Cellule {
    private boolean bombe;
    private boolean revelee;
    private int bombesAdjacentes;
    private boolean marquee;


    public Cellule() {
        this.bombe = false;
        this.revelee = false;
        this.bombesAdjacentes = 0;
        this.marquee = false;

    }

    public boolean contientBombe() {
        return bombe;
    }

    public void placerBombe() {
        this.bombe = true;
    }

    public boolean estRevelee() {
        return revelee;
    }

    public void reveler() {
        this.revelee = true;
    }

    public int getBombesAdjacentes() {
        return bombesAdjacentes;
    }

    public void setBombesAdjacentes(int bombesAdjacentes) {
        this.bombesAdjacentes = bombesAdjacentes;
    }

    public boolean estMarquee() {
        return marquee;
    }

    public void demarquer() {
        this.marquee = false;

    }

    public void marquer() {
        this.marquee = true;
    }
}

