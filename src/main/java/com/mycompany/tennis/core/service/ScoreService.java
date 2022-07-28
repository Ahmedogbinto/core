/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.Dto.EpreuveFullDto;
import com.mycompany.tennis.core.Dto.MatchDto;
import com.mycompany.tennis.core.Dto.ScoreFullDto;
import com.mycompany.tennis.core.Dto.TournoiDto;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ressourceUtil.HibernateUtil;

/**
 *
 * @author User
 */
public class ScoreService {
    private ScoreRepositoryImpl scoreRepository;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    
    public ScoreFullDto getScore(long id){
        Session session = null;
        Transaction tx = null;
        Score score = null;
        ScoreFullDto scoreDto = null;
       
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          score = scoreRepository.getById(id);
          
          scoreDto = new ScoreFullDto();                               // Convertion de ScoreDto en ScoreFullDto
          scoreDto.setId(score.getId());
          scoreDto.setSet1(score.getSet1());
          scoreDto.setSet2(score.getSet2());
          scoreDto.setSet3(score.getSet3());
          scoreDto.setSet4(score.getSet4());
          scoreDto.setSet5(score.getSet5());
          
          
          MatchDto matchDto = new MatchDto();
          matchDto.setId(score.getMatch().getId());
          
          scoreDto.setMatch(matchDto);
          
          EpreuveFullDto epreuveDto = new EpreuveFullDto();              // Debut de conversion de epreuveDto vers epreuveFullDto pour avoir le nom prenom du finaliste a partir de lId du match 
          epreuveDto.setId(score.getMatch().getEpreuve().getId());
          epreuveDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
          epreuveDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
   
          TournoiDto tournoiDto = new TournoiDto();                      // Debut de conversion de tournoi vers TournoiDto pour avoir le nom et le code du tournoi a partir de lId de lepreuve 
          tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
          tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
          tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
          
          epreuveDto.setTournoi(tournoiDto); 
          matchDto.setEpreuve(epreuveDto); 
          
          
         
          System.out.println("Le score a bien été affiché"); 
        }
        catch (Exception e) {
            e.printStackTrace();
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }finally {
                if (session != null) {
                    session.close();
                }
         }
        return scoreDto; 
        
    }
            
}
