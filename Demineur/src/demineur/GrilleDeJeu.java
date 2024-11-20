/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;

/**
 *
 * @author pommi
 */
public class GrilleDeJeu {
    private  cellule[][] matriceCellules;
    private int lignes;
    private int colonnes;
public GrilleDeJeu(int lignes, int colonnes){
    this.lignes=lignes;
    this.colonnes=colonnes;
    matriceCellules = new cellule[lignes][colonnes];
    for (int i = 0; i<lignes; i++) {
        for (int j = 0; j<colonnes; j++){
            matriceCellules[i][j] = new cellule(false);
        }
    }
}
public void AjouterBombes(int ligne, int colonne){
    if(ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes){
        matriceCellules[ligne][colonne].setContientBombe(true);
    }
}
}

