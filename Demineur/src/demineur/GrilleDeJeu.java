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

    private Cellule[][] matriceCellules;
    private int lignes;
    private int colonnes;
    private int Bombes;

    public GrilleDeJeu(int lignes, int colonnes, int bombes) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        matriceCellules = new Cellule[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                matriceCellules[i][j] = new Cellule(false);
            }
        }
        this.Bombes = bombes;
    }

    public int getNbLignes() {
        return lignes;
    }

    public int getNbColonnes() {
        return colonnes;
    }

    public int isBombes() {
        return Bombes;
    }

    public void PlacerBombes() {
        Random random = new Random();
        int BombesPlacees = 0;
        while (BombesPlacees < Bombes) {
            int i = random.nextInt(lignes);
            int j = random.nextInt(colonnes);
            if (!matriceCellules[i][j].isBombe()) {
                matriceCellules[i][j].setajouterbombe();
                BombesPlacees++;
            }
        }
    }

    public void BombesVoisines() {
        int[] directions = {-1, 0, 1};
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (!matriceCellules[i][j].isBombe()) {
                    int bombesvoisines = 0;
                    for (int di : directions) {
                        for (int dj : directions) {
                            if (di == 0 && dj == 0) {
                                continue;
                            }
                            int voisinX = j + di;
                            int voisinY = j + dj;

                            if (voisinX >= 0 && voisinX < lignes && voisinY >= 0 && voisinY < colonnes) {
                                if (matriceCellules[voisinX][voisinY].isBombe()) {
                                    bombesvoisines++;
                                }
                            }
                        }
                    }
                    matriceCellules[i][j].setBombesVoisines(bombesvoisines);
                }
            }
        }
    }

    public void revelerCellule(int ligne, int colonne) {
        if (ligne < 0 || ligne >= lignes || colonne < 0 || colonne >= colonnes) {
            return;
        }
        Cellule cellule = matriceCellules[ligne][colonne];
        if (cellule.isreveal()) {
            return;
        }
        cellule.setreveal(true);
        if (cellule.isBombe()) {
            throw new RuntimeException("Bombe révélée !! Fin de la partie !");
        }
        if (cellule.getvoisin() == 0) {
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj < +1; dj++) {
                    if (di != 0 || dj != 0) {
                        revelerCellule(ligne + di, colonne + dj);
                    }
                }
            }
        }
    }

    public boolean getPresenceBombes(int i, int j) {
        return matriceCellules[i][j].isBombe();
    }

    public boolean ToutesCellules() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (!matriceCellules[i][j].isBombe() && !matriceCellules[i][j].isreveal()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void afficherGrille() {
        for (int i = 0; i < matriceCellules.length; i++) {
            for (int j = 0; j < matriceCellules[i].length; j++) {
                System.out.print(matriceCellules[i][j] + " ");
            }
            System.out.println();
        }
    }
}
