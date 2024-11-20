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
        Partie partie = new Partie();

        // Initialiser une partie avec une grille de 5x5, 5 bombes, et 3 vies
        partie.initaliserPartie(5, 5, 5);

        // Démarrer la partie
        partie.demarrerPartie();
    }

}
