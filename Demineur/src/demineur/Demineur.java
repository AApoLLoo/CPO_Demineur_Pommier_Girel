/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demineur;

/**
 *
 * @author Antoine Girel/Pommier
 */
public class Demineur {

    /**
     *
     */
    public static void main(String[] args) {

        Partie partie = new Partie(10, 10, 10, 3);
        GrilleDeJeu grille = new GrilleDeJeu(10,10,10);
        FenetreInterfaceGraphique interfaceGraphique = new FenetreInterfaceGraphique(grille);
        interfaceGraphique.show();
        partie.menu();
    }

}
