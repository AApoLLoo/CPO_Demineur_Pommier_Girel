/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;

/**
 *Cellule Demineur
 * @author danie
 */
public class Cellule {
    boolean Bombe ;
    boolean reveal;
    int voisin;
//Constructeur
public Cellule(boolean Bombe){
this.Bombe=Bombe;
this.reveal=false;
this.voisin=0;
}

//Methode
public String isreveal (){
    this.reveal=true;
    if (Bombe=true){
        return "il y a une bombe";
    }else{
        return String.valueOf(voisin);
            
        }
    }    
public void ajouterbombe (){
    this.voisin++;
}
public void placerbombe(boolean Bombe){
    this.Bombe=Bombe;
    
}
        
//Accesseurs 
public boolean  getBombe(){
    return Bombe ;   
}       
        
public boolean getreveal(){
    return reveal;
}

public int getvoisin(){  
    return voisin ;
}

@Override
public String toString(){
    if (reveal){
        return isreveal();
    }else{
        return "■";
    }
}
}

    
