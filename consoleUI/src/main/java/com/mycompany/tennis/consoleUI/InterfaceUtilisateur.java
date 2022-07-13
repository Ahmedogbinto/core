/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.consoleUI;

import com.mycompany.tennis.Controller.JoueurController;
import com.mycompany.tennis.Controller.TournoiController;

/**
 *
 * @author User
 */
public class InterfaceUtilisateur {
    
    public static void main(String... args){
        
       JoueurController joueurController=new JoueurController(); 
         /*joueurController.afficherDetailsJoueur();*/                   // Lire un joueur en connaissanbt son id
             joueurController.creerJoueur();                          // Créer un joueur
  //------------------------------------------------------------------------------------------------------------------- 
       /*TournoiController tournoiController = new TournoiController();
       tournoiController.afficherDetailsTournoi();
      /*tournoiController.creerTournoi();*/
         
    }
    
}
