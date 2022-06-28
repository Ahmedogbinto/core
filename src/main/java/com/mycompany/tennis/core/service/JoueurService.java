/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

/**
 *
 * @author User
 */
public class JoueurService {
    private JoueurRepositoryImpl joueurRepository;
    
    public JoueurService(){
        this.joueurRepository=new JoueurRepositoryImpl();
    }
    
    public void create(Joueur joueur){
       joueurRepository.createJoueur(joueur);// cette methode ne fait juste qu'appelée la methode createJoueur
    }
}