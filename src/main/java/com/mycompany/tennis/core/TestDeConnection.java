/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import java.util.List;

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
      JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
      Joueur joueur=new Joueur();
      joueur.setNom("Noah");
      joueur.setPrenom("Yannik");
      joueur.setSexe('H');
      joueurRepository.createJoueur(joueur);
      
      System.out.println("L'identifant du joueur crée est "+ joueur.getId());
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
}
    
}