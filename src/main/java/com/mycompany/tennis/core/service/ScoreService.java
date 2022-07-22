/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
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

    
    public Score getScore(long id){
        Session session = null;
        Transaction tx = null;
        Score score = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          score = scoreRepository.getById(id);
         
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
        return score; 
        
    }
            
}
