/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;
import java.util.Scanner;
/** PARTIE
 *
 * @author danie
 */
public class Partie {
    private GrilleDeJeu grilledejeu;
    private boolean finjeu;
    private boolean Victoire;
    
    
    // Constructeur//
    public Partie(int ligne, int colonne){
        this.grilledejeu=new GrilleDeJeu(ligne,colonne);
        this.finjeu=false;
        this.Victoire=false;
    // Methode//    
    }     
      public boolean estDansLaGrille(int ligne, int colonne) {
        return ligne >= 0 && ligne < ligne && colonne >= 0 && colonne < colonne;}  
    
    public boolean revelerCellule(int ligne, int colonne) {
        Cellule cellule = grille[ligne][colonne];
        if (!cellule.isreveal()) {
            cellule.revelerCellule();
            return true;
        }
        
        public boolean isVictoire() {
        // Vérifie si toutes les cellules sûres sont découvertes
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                Cellule cellule = grille[i][j];
                if (!cellule.isreveal() && !cellule.contientBombe()) {
                    return false;
                }
            }
        }
        return true;
    }
        
        
         public void afficherGrille(boolean revelerTout) {
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if (revelerTout) {
                    System.out.print(grilledejeu[i][j].toString() + " ");
                } else {
                    System.out.print(grilledejeu[i][j].toString() + " ");
                }
            }
            System.out.println();
        }
    }
        
        public Cellule getCellule(int ligne, int colonne) {
        return grilledejeu[ligne][colonne];
    }
}
        
        
        
        
        
        
    
       public void lancer_jeu(){
           Scanner scanner = new Scanner(System.in);
           System.out.println("bienvenue dans Demineur");
           grilledejeu.afficherGrille(false);
           
         while(!finjeu){
             System.out.println("Saisir Coordonnées Case à réveler:");
             int ligne=scanner.nextInt();
             int colonne=scanner.nextInt();
             
              if (!grilledejeu.estDansLaGrille(ligne, colonne)) {
                System.out.println("Coordonnées invalides, essayez encore.");
                continue;
            }
              
               }

            if (grilledejeu.revelerCellule(ligne, colonne)) {
                if (grilledejeu.getCellule(ligne, colonne).contientBombe()) {
                    finjeu = true;
                    Victoire = false;
                } else if (grilledejeu.estVictoire()) {
                    finjeu = true;
                    Victoire = true;
                }
            } else {
                System.out.println("La cellule est déjà révélée, essayez une autre.");
            }

            grilledejeu.afficherGrille(false); // Met à jour la grille après chaque tour
        }

        if (Victoire){
            System.out.println("\nBravo ! Vous avez découvert toutes les cellules sûres. Victoire !");
        } else {
            System.out.println("\nBoom ! Vous avez déclenché une bombe. Défaite.");
            grilledejeu.afficherGrille(true); 
        }

        scanner.close();
    }

         
                


    

