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
    private  Cellule[][] matriceCellules;
    private int lignes;
    private int colonnes;
public GrilleDeJeu(int lignes, int colonnes){
    this.lignes=lignes;
    this.colonnes=colonnes;
    matriceCellules = new Cellule[lignes][colonnes];
    for (int i = 0; i<lignes; i++) {
        for (int j = 0; j<colonnes; j++){
            matriceCellules[i][j] = new Cellule(false);
        }
    }
}
public void AjouterBombes(int ligne, int colonne){
    if(ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes){
        matriceCellules[ligne][colonne].setplacerbombe(true);
    }
}
public void BombesVoisines(){
    int [] directions = {-1, 0, 1};
    for (int i = 0; i < lignes; i++){
        for (int j =0; j < colonnes; j++){
            if (!matriceCellules[i][j].getBombe()){
                int bombesvoisines = 0;
                for (int di : directions){
                    for(int dj : directions){
                        if (di == 0 && dj == 0)continue;
                        int voisinX = j + di;
                        int voisinY =j + dj;
                        
                        if (voisinX >= 0 && voisinX < lignes && voisinY >=0 && voisinY < colonnes){
                            if (matriceCellules[voisinX][voisinY].getBombe()){
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
public void afficherGrille(){
    for (int i = 0; i < lignes; i++){
        for (int j = 0; j < colonnes; j++){
            if (matriceCellules[i][j].getBombe()){
                System.out.println(" * ");
            } else {
                System.out.println(" " + matriceCellules[i][j].getBombe()+ " ");
            }
        }
        System.out.println();
    }
}
}


