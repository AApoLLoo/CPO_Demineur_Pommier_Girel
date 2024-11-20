/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demineur;

/**
 *
 * @author pommi
 */
public class Demineur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GrilleDeJeu grille = new GrilleDeJeu(5, 5, 5);
        grille.PlacerBombes();
        grille.BombesVoisines();

        System.out.println("Grille initiale :");
        System.out.println(grille);

        try {
            grille.revelerCellule(2, 2);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Grille après révélation :");
        System.out.println(grille);

        System.out.println("Toutes les cellules sûres sont révélées : " + grille.ToutesCellules());
    }

}
