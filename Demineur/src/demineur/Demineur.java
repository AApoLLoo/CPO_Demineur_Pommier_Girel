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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InterfaceGraphique interfaceGraphique = new InterfaceGraphique();
        Partie partie = new Partie(10, 10, 10, 3);
        interfaceGraphique.show();
        partie.menu();
    }

}
