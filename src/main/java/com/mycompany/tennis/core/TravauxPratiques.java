/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

/**
 *
 * @author User
 */
public class TravauxPratiques {
     public static void main(String... args){
     // la liste des joueurs   
    /*TournoiRepositoryImpl tournoiRepository=new TournoiRepositoryImpl();
    List<Tournoi> liste=tournoiRepository.lister();
    
    for (Tournoi tournoi:liste){
    System.out.println(tournoi.getId()+" "+tournoi.getNom()+" "+tournoi.getCode());
     }*/
    //----------------------------------------------------------------------------------------------------------
    // lire un joueur
     /*JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
    Joueur joueur=joueurRepository.getById(43L);
    
    System.out.println(joueur.getPrenom()+" "+joueur.getNom());*/
      
   //----------------------------------------------------------------------------------------------------------
   // créer un nouveau Tournoi
        /*TournoiRepositoryImpl tournoiRepository=new TournoiRepositoryImpl();
     
    Tournoi tournoi=new Tournoi();
    tournoi.setNom("AFrican Tennis");
    tournoi.setCode("AT");
    tournoiRepository.createTournoi(tournoi);*/
   //----------------------------------------------------------------------------------------------------------
   // Lire un tournoi
       /* TournoiRepositoryImpl tournoiRepository=new TournoiRepositoryImpl();
        Tournoi tournoi=tournoiRepository.getById(3L);*/
  
     }
     
     
}
