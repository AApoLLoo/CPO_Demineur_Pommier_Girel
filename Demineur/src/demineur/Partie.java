/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;
import java.util.Scanner;

/**
 * PARTIE
 *
 * @author danie
 */

    public class Partie {
    private GrilleDeJeu grille;
    private boolean enCours;

    public Partie(int lignes, int colonnes, int nombreDeBombes) {
        grille = new GrilleDeJeu(lignes, colonnes, nombreDeBombes);
        enCours = true;
    }

    public void jouer() {
        Scanner scanner = new Scanner(System.in);

        while (enCours) {
            grille.afficherGrille();
            System.out.print("Entrez la ligne et la colonne à révéler (ex: 0 1): ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();

            if (grille.estBombe(ligne, colonne)) {
                System.out.println("BOUM! Vous avez cliqué sur une bombe. Vous avez perdu.");
                enCours = false;
            } else {
                grille.revelerCellule(ligne, colonne);
                if (grille.aGagne()) {
                    System.out.println("Félicitations! Vous avez révélé toutes les cellules sans bombe. Vous avez gagné!");
                    enCours = false;
                }
            }
        }

        grille.afficherGrille();
        scanner.close();
    }
}

    


