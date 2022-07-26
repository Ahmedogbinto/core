/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.Dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import java.util.HashSet;
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
    
    public void create(TournoiDto dto){
         Session session = null;
         Transaction tx = null;
   
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            
            Tournoi tournoi = new Tournoi();                                // debut de conversion de l'entit� tournoi en dto
            tournoi.setId(dto.getId());                                     // les propri�t�s du dto vers celles de l'entit�es
            tournoi.setCode(dto.getCode());
            tournoi.setNom(dto.getNom());
            
            tournoiRepository.createTournoi(dto);
            tx.commit();

            System.out.println("Un nouveau tournoi a bien �t� cr��");
            System.out.println("L'identifiant du tournoi cr�� est " + tournoi.getId());
        }
            catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }
    
    public TournoiDto getTournoi(Long id){
        Session session = null;
        Transaction tx = null;
        Tournoi tournoi = null;
        TournoiDto dto = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace � cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          tournoi = tournoiRepository.getById(id);
          
          dto = new TournoiDto();
          dto.setId(tournoi.getId());
          dto.setCode(tournoi.getCode());
          dto.setNom(tournoi.getNom());
            
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
        return dto; 
       
    }
    
    public void delete(long id){
        Session session = null;
        Transaction tx = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace � cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          
         tournoiRepository.deleteTournoi(id);
          tx.commit();
          
          System.out.println("Le tournoi a bien �t� supprim� de la base de donnees");  
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





