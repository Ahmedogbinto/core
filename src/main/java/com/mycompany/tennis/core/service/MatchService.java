/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Match;
import dao.MatchDaoImpl;

/**
 *
 * @author User
 */
public class MatchService {
    // cette classe service va béneficier de deux Repository MatchRepo et ScoreRepo 
    
    /*private MatchRepositoryImpl matchRepository=new MatchRepositoryImpl();
    private ScoreRepositoryImpl scoreRepository=new ScoreRepositoryImpl();*/
    
    MatchDaoImpl matchDao;
    public MatchService(){ 
        /*this.scoreRepository=new ScoreRepositoryImpl();
        this.matchRepository=new MatchRepositoryImpl();*/
    matchDao = new MatchDaoImpl();
    }
    
     
    public void enregistrerNouveauMatch(Match match){
        /*matchRepository.createMatch(match);                                         // creer un enregistrement dans la table match pour recuperer son id
        scoreRepository.createScore(match.getScore());*/          // Ensuite scorerepository qui dispose d<une clee vers le match pour connaitre le score du match
        matchDao.createMatchWithScore(match);
    }
}
