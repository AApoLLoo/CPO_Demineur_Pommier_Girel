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
    private boolean finjeu;
    private boolean Victoire;
    private int nbBombes;

public void initaliserPartie(int nbLignes,int nbColonnes, int nbBombes){
    this.grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
    this.Victoire = false;
    grille.PlacerBombes();
    grille.BombesVoisines();
}
public boolean verifierVictoire() {
        return grille.ToutesCellules();
    }
public void Tour(int ligne, int colonne){
    if (Victoire){
        System.out.println("La partie est terminée.");
        return;
    }
    try {
            grille.revelerCellule(ligne, colonne);
            System.out.println("Cellule (" + ligne + ", " + colonne + ") révélée avec succès.");
        } catch (RuntimeException e) {
            System.out.println("BOOM ! Vous avez révélé une bombe. Il vous reste tant de vie(s).");
        }

        if (verifierVictoire()) {
            Victoire = true;
            System.out.println("Félicitations ! Vous avez révélé toutes les cellules sûres. Vous avez gagné !");
        }
    }
public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu des mines !");
        System.out.println("Voici la grille initiale :");
        System.out.println(grille);

        while (!Victoire) {
            System.out.println("Options :");
            System.out.println("1. Révéler une cellule");
            System.out.println("2. Afficher le nombre de vies restantes");
            System.out.println("3. Afficher la grille");
            System.out.println("4. Quitter la partie");

            System.out.print("Entrez votre choix : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1 -> {
                    System.out.print("Entrez la ligne : ");
                    int ligne = scanner.nextInt();
                    System.out.print("Entrez la colonne : ");
                    int colonne = scanner.nextInt();
                    Tour(ligne, colonne);
                }
                case 2 -> System.out.println("Grille actuelle :\n" + grille);
                case 3 -> {
                    Victoire = true;
                    System.out.println("Merci d'avoir joué. À bientôt !");
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }

            if (!Victoire) {
                System.out.println("Grille actuelle :");
                System.out.println(grille);
            }
        }

        scanner.close();
    }
}

    


