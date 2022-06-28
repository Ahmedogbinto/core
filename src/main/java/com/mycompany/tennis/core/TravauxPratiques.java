/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import java.util.List;

/**
 *
 * @author User
 */
public class TravauxPratiques {
     public static void main(String... args){
         
         TournoiRepositoryImpl tournoiRepository=new TournoiRepositoryImpl();
    List<Tournoi> liste=tournoiRepository.lister();
    
    for (Tournoi tournoi:liste){
    System.out.println(tournoi.getId()+" "+tournoi.getNom()+" "+tournoi.getCode());
    
     }
     }
     
     
}
