/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;

import java.util.Random;

/**
 *
 * @author pommi
 */
public class GrilleDeJeu {
    private final Cellule[][] grille;
    private final int lignes;
    private final int colonnes;
    private final int nombreDeBombes;

    public GrilleDeJeu(int lignes, int colonnes, int nombreDeBombes) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.nombreDeBombes = nombreDeBombes;
        this.grille = new Cellule[lignes][colonnes];

        // Initialiser la grille avec des cellules vides
        Random rand = new Random();
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (rand.nextDouble() < 0.2) { // 20% des cases sont des murs
                    grille[i][j] = null;
                } else {
                    grille[i][j] = new Cellule();
                }
            }
        }

        // Placer les bombes de manière aléatoire
        placerBombes();
        // Calculer le nombre de bombes adjacentes pour chaque cellule
        calculerBombesAdjacentes();
    }

    private void placerBombes() {
        Random rand = new Random();
        int bombesPlacees = 0;

        while (bombesPlacees < nombreDeBombes) {
            int ligne = rand.nextInt(lignes);
            int colonne = rand.nextInt(colonnes);

            if (!grille[ligne][colonne].contientBombe()) {
                grille[ligne][colonne].placerBombe();
                bombesPlacees++;
            }
        }
    }

    private void calculerBombesAdjacentes() {
        int[] directions = {-1, 0, 1};

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (!grille[i][j].contientBombe()) {
                    int bombesAdjacentes = 0;

                    for (int di : directions) {
                        for (int dj : directions) {
                            int ligneAdj = i + di;
                            int colAdj = j + dj;

                            if (ligneAdj >= 0 && ligneAdj < lignes && colAdj >= 0 && colAdj < colonnes) {
                                if (grille[ligneAdj][colAdj].contientBombe()) {
                                    bombesAdjacentes++;
                                }
                            }
                        }
                    }

                    grille[i][j].setBombesAdjacentes(bombesAdjacentes);
                }
            }
        }
    }

    public void revelerCellule(int ligne, int colonne) {
        if (ligne < 0 || ligne >= lignes || colonne < 0 || colonne >= colonnes || grille[ligne][colonne].estRevelee()) {
            return;
        }

        grille[ligne][colonne].reveler();
        if (grille[ligne][colonne].getBombesAdjacentes() == 0) {
            int[] directions = {-1, 0, 1};
            for (int di : directions) {
                for (int dj : directions) {
                    revelerCellule(ligne + di, colonne + dj);
                }
            }
        }
    }

    public boolean estBombe(int ligne, int colonne) {
        return grille[ligne][colonne].contientBombe();
    }

    public boolean aGagne() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (!grille[i][j].contientBombe() && !grille[i][j].estRevelee()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void afficherGrille() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (grille[i][j] == null) {
                    System.out.print("# "); // Mur
                } else if (grille[i][j].estRevelee()) {
                    if (grille[i][j].contientBombe()) {
                        System.out.print("* ");
                    } else {
                        System.out.print(grille[i][j].getBombesAdjacentes() + " ");
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

}
