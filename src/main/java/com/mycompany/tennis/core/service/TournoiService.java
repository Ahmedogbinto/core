/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

/**
 *
 * @author User
 */
public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;
    
    
    public TournoiService(){
        this.tournoiRepository=new TournoiRepositoryImpl();
    }
    
    public void create(Tournoi tournoi){
        tournoiRepository.createTournoi(tournoi);
    }
    
    public void getTournoi(Long id){
        tournoiRepository.getById(id);
    }
}
