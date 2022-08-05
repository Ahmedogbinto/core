/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.entity.Match;
import java.util.HashSet;
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
      
      
         System.out.println("Un nouveau match a bien été créé et ajouté"); 
    }
  
  public Match getById(Long id){
        Session session = null;
        Match match = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        match=session.get(Match.class,id);

        System.out.println("le Match a été bien affiché");
        
        return match; 
    }
  
  public void delete(Long id){
      
      Session session = HibernateUtil.getSessionFactory().getCurrentSession();
      Match match=session.get(Match.class,id);
      session.delete(match);
      
      System.out.println("Le match a bien ete supprime");
  }
}
  
  