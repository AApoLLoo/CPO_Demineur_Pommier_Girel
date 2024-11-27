/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;
import java.util.Scanner;

/**
 * PARTIE
 *
 * @author Antoine Girel/Pommier
 */

    public class Partie {
    private final GrilleDeJeu grille;
    private boolean enCours;

    public Partie(int lignes, int colonnes, int nombreDeBombes) {
        grille = new GrilleDeJeu(lignes, colonnes, nombreDeBombes);
        enCours = true;
    }

    public void jouer() {
        Scanner scanner = new Scanner(System.in);

        while (enCours) {
            System.out.println("Options :");
            System.out.println("1. Révéler une cellule");
            System.out.println("2. Afficher le nombre de vies restantes");
            System.out.println("3. Afficher la grille");
            System.out.println("4. Quitter la partie");
            System.out.print("Entrez votre choix : ");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
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
                    grille.afficherGrille();
                    break;
                case 2:
                    System.out.println("Il vous reste "); //+ grille.getVies() + " vies.");
                    break;
                case 3:
                    grille.afficherGrille();
                    break;
                case 4:
                    enCours = false;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
            }
        grille.afficherGrille();
        scanner.close();
    }
}

    


