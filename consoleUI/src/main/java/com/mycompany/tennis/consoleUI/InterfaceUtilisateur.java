/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.consoleUI;

import com.mycompany.tennis.Controller.JoueurController;

/**
 *
 * @author User
 */
public class InterfaceUtilisateur {
    
    public static void main(String... args){
        
       JoueurController joueurController=new JoueurController();     
         /* joueurController.afficherDetailsJoueur();                          // Lire un joueur en connaissanbt son id
         /* joueurController.creerJoueur();*/                                // Créer un joueur
         /*joueurController.renommeJoueur();*/   
         /*joueurController.ChangerSexeJoueur();*/                          // Changer le sexe d'un joueur
         /*joueurController.SupprimerJoueur();*/                           // Supprimer un joueur
        
         joueurController.afficherListeJoueurs();  
//------------------------------------------------------------------------------------------------------------------- 
        /* TournoiController tournoiController = new TournoiController();*/
        /* tournoiController.afficherDetailsTournoi(); */
        /* tournoiController.creerUnTournoi(); */
        /* tournoiController.supprimerTournoi();*/
//----------------------------------------------------------------------------------------------------------     
        /*ScoreController scoreController = new ScoreController();*/
        /*scoreController.afficherDetailsScore();*/
 //------------------------------------------------------------------------------------------------------------
   
        /*EpreuveController epreuveController = new EpreuveController();*/   
        /*epreuveController.afficherDetailsEpreuve();*/ 
        /*epreuveController.afficherEpreuve();*/
        /* epreuveController.afficherListEpreuve();*/
 //---------------------------------------------------------------------------------------------------------
   
         /* MatchController matchController = new MatchController();
            matchController.supprimerMatch(); */ 
//---------------------------------------------------------------------------------------------------------
         /* ScoreController scoreController = new ScoreController();
            scoreController.supprimerScore();*/    
    }
    
    
}

