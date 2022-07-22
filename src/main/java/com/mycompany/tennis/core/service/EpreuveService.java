/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ressourceUtil.HibernateUtil;

/**
 *
 * @author User
 */
public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }
    
    public Epreuve getEpreuveAvecTournoi(Long id){
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace � cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          epreuve = epreuveRepository.getById(id);
         
          Hibernate.initialize(epreuve.getTournoi());                 // C'est comme si vous aviez l'un des getters de la classe HibernateProxy
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
        return epreuve;      
    }
    
     public Epreuve getEpreuveSansTournoi(Long id){
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace � cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          epreuve = epreuveRepository.getById(id);
           
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
        return epreuve; 
        
    }
    
    
    
    
}
