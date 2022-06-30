/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.MatchService;

/**
 *
 * @author User
 */

public class TestDeConnection {
    public static void main(String... args){
        
        // lecture d'un joueur
      /*JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
       Joueur joueur=joueurRepository.getById(21L);
       
       System.out.println(joueur.getPrenom()+" "+joueur.getNom());*/
//-------------------------------------------------------------------------------
      // Créer un joueur
      /*JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl(); // Interaction avec le Repository 
      Joueur joueur=new Joueur();
      joueur.setNom("Noah");
      joueur.setPrenom("Yannik");
      joueur.setSexe('H');
      joueurRepository.createJoueur(joueur);*/
 //-------------------------------------------------------------------------------
      //Modifier un joueur
      /*JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
      Joueur joueur = joueurRepository.getById(59L);
      joueur.setPrenom("Yannick");
      joueurRepository.updateJoueur(joueur);*/
 //-------------------------------------------------------------------------------
      // Supprimer le joueur
      /*JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
      joueurRepository.deleteJoueur(59L);*/
    
 //-------------------------------------------------------------------------------
     /*JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
    List<Joueur> liste=joueurRepository.lister();
    
    for (Joueur joueur:liste){
    System.out.println(joueur.getPrenom()+" "+joueur.getNom());
    }*/
 //-------------------------------------------------------------------------------
 // Creer un match a partir de matchrepository  et creer match  a partir de scoreRepository 
 
     MatchService matchService =new MatchService(); // Interaction avec le Repository 
     Match match=new Match();
     Score score=new Score();
     score.setSet1((byte)3);
     score.setSet2((byte)4);
     score.setSet3((byte)3);
     
     match.setScore(score); // j'affectes les scores au match
     score.setMatch(match); // parce que la relation est bidirectionnelle l'un permet de trouver l'autre
     
     Joueur joueur1=new Joueur();
     joueur1.setId(32L);
     Joueur joueur2=new Joueur();
     joueur2.setId(34L);
     
     match.setVainqueur(joueur1);
     match.setFinaliste(joueur2);
     
     Epreuve epreuve=new Epreuve();
     epreuve.setId(9L);
     match.setEpreuve(epreuve);
     
     matchService.enregistrerNouveauMatch(match);
     
   
     
    }
}