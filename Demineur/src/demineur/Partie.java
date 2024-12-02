/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;
import javax.swing.*;
import java.util.Scanner;

/**
 * PARTIE DEMINEUR
 * @author Antoine Girel/Pommier
 */

    public class Partie {
    private final GrilleDeJeu grille;
    private boolean enCours;
    private int vies;

    public Partie(int lignes, int colonnes, int nombreDeBombes, int vies) {
        grille = new GrilleDeJeu(lignes, colonnes, nombreDeBombes);
        enCours = true;
        this.vies = vies;
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        clearConsole();
        System.out.println("Options :");
        System.out.println("1. Révéler une cellule");
        System.out.println("2. Afficher le nombre de vies restantes");
        System.out.println("3. Afficher la grille");
        System.out.println("4. Quitter la partie");
        System.out.print("Entrez votre choix : ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                jouer();
                break;
            case 2:
                System.out.println("Il vous reste " + vies + " vies.");
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
        scanner.close();
    }

    public int getVies() {
        return vies;
    }

    public GrilleDeJeu getGrille() {
        return grille;
    }

    public void jouer() {
        while (enCours) {

            Scanner scanner = new Scanner(System.in);
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Entrez la ligne et la colonne à révéler (ex: 0 1): ");
                int ligne = scanner.nextInt();
                int colonne = scanner.nextInt();
                if (ligne >= 0 && ligne < grille.getLignes() && colonne >= 0 && colonne < grille.getColonnes()) {
                    validInput = true;
                    if (grille.estBombe(ligne, colonne)) {
                        System.out.println("BOUM! Vous avez cliqué sur une bombe. Vous avez perdu une vie.");
                        vies -= 1;
                        if (vies == 0) {
                            System.out.println("Vous n'avez plus de vies. Vous avez perdu.");
                            System.out.println("Le nombre de vies restantes était de " + vies);
                            enCours = false;
                        }
                    } else {
                        grille.revelerCellule(ligne, colonne);
                        if (grille.aGagne()) {
                            System.out.println("Félicitations! Vous avez révélé toutes les cellules sans bombe. Vous avez gagné!");
                            enCours = false;
                        }
                    }
                    grille.afficherGrille();
                } else {
                    System.out.println("Coordonnées invalides.");
                }
            }
        }
    }
}
    


