/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.Dto.EpreuveFullDto;
import com.mycompany.tennis.core.Dto.JoueurDto;
import com.mycompany.tennis.core.Dto.MatchDto;
import com.mycompany.tennis.core.Dto.ScoreFullDto;
import com.mycompany.tennis.core.Dto.TournoiDto;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ressourceUtil.HibernateUtil;

/**
 *
 * @author User
 */
public class MatchService {
    // cette classe service va béneficier de deux Repository MatchRepo et ScoreRepo 
    
    private MatchRepositoryImpl matchRepository=new MatchRepositoryImpl();
    private ScoreRepositoryImpl scoreRepository=new ScoreRepositoryImpl();
    

    public MatchService(){ 
        this.scoreRepository=new ScoreRepositoryImpl();
        this.matchRepository=new MatchRepositoryImpl();

    }
    
     
    public void enregistrerNouveauMatch(Match match){
        matchRepository.createMatch(match);                                         // creer un enregistrement dans la table match pour recuperer son id
        scoreRepository.createScore(match.getScore());                             // Ensuite scorerepository qui dispose d<une clee vers le match pour connaitre le score du match
        
    }
    
     public MatchDto getMatch(Long id){
        Session session = null;
        Transaction tx = null;
        Match match = null;
        MatchDto matchDto = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          match = matchRepository.getById(id);
          
          matchDto = new MatchDto();
          matchDto.setId(match.getId());
          
          JoueurDto finalisteDto = new JoueurDto();                    // Debut de conversion de finaliste en Dto pour avoir le nom prenom du finaliste a partir de lId du match     
          finalisteDto.setId(match.getFinaliste().getId());
          finalisteDto.setNom(match.getFinaliste().getNom());
          finalisteDto.setPrenom(match.getFinaliste().getPrenom());
          finalisteDto.setSexe(match.getFinaliste().getSexe());
          matchDto.setFinaliste(finalisteDto);
          
          JoueurDto vainqueurDto = new JoueurDto();                     // Debut de conversion de vainqueur en Dto pour avoir le nom prenom du Vainqueur a partir de lId du match 
          vainqueurDto.setId(match.getVainqueur().getId());
          vainqueurDto.setNom(match.getVainqueur().getNom());
          vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
          vainqueurDto.setSexe(match.getVainqueur().getSexe());
          matchDto.setVainqueur(vainqueurDto);
          
          EpreuveFullDto epreuveDto = new EpreuveFullDto();              // Debut de conversion de epreuveDto vers epreuveFullDto pour avoir le nom prenom du finaliste a partir de lId du match 
          epreuveDto.setId(match.getEpreuve().getId());
          epreuveDto.setAnnee(match.getEpreuve().getAnnee());
          epreuveDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
   
          TournoiDto tournoiDto = new TournoiDto();                      // Debut de conversion de tournoi vers TournoiDto pour avoir le nom et le code du tournoi a partir de lId de lepreuve 
          tournoiDto.setId(match.getEpreuve().getTournoi().getId());
          tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
          tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
          
          epreuveDto.setTournoi(tournoiDto); 
          matchDto.setEpreuve(epreuveDto); 
          
          ScoreFullDto scoreDto = new ScoreFullDto();
          scoreDto.setId(match.getScore().getId());
          scoreDto.setSet1(match.getScore().getSet1());
          scoreDto.setSet2(match.getScore().getSet2());
          scoreDto.setSet3(match.getScore().getSet3());
          scoreDto.setSet4(match.getScore().getSet4());
          scoreDto.setSet5(match.getScore().getSet5());
          
          matchDto.setScore(scoreDto);
          scoreDto.setMatch(matchDto);
          tx.commit();
          
          System.out.println("Le tournoi est bien lu");
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
        return matchDto; 
       
    }
}
