/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.Dto.JoueurDto;
import com.mycompany.tennis.core.Dto.MatchDto;
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
          
          JoueurDto finalisteDto = new JoueurDto();
          finalisteDto.setId(match.getFinaliste().getId());
          finalisteDto.setNom(match.getFinaliste().getNom());
          finalisteDto.setPrenom(match.getFinaliste().getPrenom());
          finalisteDto.setSexe(match.getFinaliste().getSexe());
          matchDto.setFinaliste(finalisteDto);
          
          JoueurDto vainqueurDto = new JoueurDto();
          vainqueurDto.setId(match.getVainqueur().getId());
          vainqueurDto.setNom(match.getVainqueur().getNom());
          vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
          vainqueurDto.setSexe(match.getVainqueur().getSexe());
          matchDto.setVainqueur(vainqueurDto);
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
