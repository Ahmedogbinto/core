/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ressourceUtil.HibernateUtil;

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
    
    public Tournoi getTournoi(Long id){
       return tournoiRepository.getById(id);
    }
    
    public void delete(long id){
        Session session = null;
        Transaction tx = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          
         tournoiRepository.deleteTournoi(id);
          tx.commit();
          
          System.out.println("Le tournoi a bien été supprimé de la base de donnees");  
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
    }
}
