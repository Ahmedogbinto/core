/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.entity.Match;
import org.hibernate.Session;
import ressourceUtil.HibernateUtil;

/**
 *
 * @author User
 */
public class MatchRepositoryImpl {
    
  public void create(Match match){
         Session session = null;
     
         session=HibernateUtil.getSessionFactory().getCurrentSession();
         session.persist(match);
      
         System.out.println("Un nouveau match a bien �t� cr�� et ajout�"); 
    }
  
  public Match getById(Long id){
        Session session = null;
        Match match = null;
             session = HibernateUtil.getSessionFactory().getCurrentSession();
             match=session.get(Match.class,id);
             
             System.out.println("le Match a �t� bien affich�");
        
        return match;
        
    }
}
  
  