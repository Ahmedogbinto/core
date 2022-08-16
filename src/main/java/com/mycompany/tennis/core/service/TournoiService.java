/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.Dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ressourceUtil.EntityManagerHolder;

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
    
    public void createTournoi(TournoiDto dto){
            EntityManager em =null;
            EntityTransaction tx = null;
   
        try {
          
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            Tournoi tournoi = new Tournoi();                                // debut de conversion de l'entité tournoi en dto
            tournoi.setId(dto.getId());                                     // les propriétés du dto vers celles de l'entitées
            tournoi.setCode(dto.getCode());
            tournoi.setNom(dto.getNom());
            
            tournoiRepository.create(tournoi);
            tx.commit();

            System.out.println("Un nouveau tournoi a bien été créé");
            System.out.println("L'identifiant du tournoi créé est " + tournoi.getId());
        }
            catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {

            if (em != null) {
                em.close();
            }
        }
    }
    
    public TournoiDto getTournoi(Long id){
        //Session session = null;
        EntityManager em =null;
        EntityTransaction tx = null;
        Tournoi tournoi = null;
        TournoiDto tournoiDto = null;
        
        try{
            //session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update.
         
          em = EntityManagerHolder.getCurrentEntityManager();
          tx=em.getTransaction();
          tx.begin();
          tournoi = tournoiRepository.getById(id);
          
          tournoiDto = new TournoiDto();
          tournoiDto.setId(tournoi.getId());
          tournoiDto.setCode(tournoi.getCode());
          tournoiDto.setNom(tournoi.getNom());
            
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
                if (em != null) {
                    em.close();
                }
         }
        return tournoiDto;   
    }
    
    public void delete(long id){
        EntityManager em =null;
        EntityTransaction tx = null;
        
        try{
          em = EntityManagerHolder.getCurrentEntityManager();
          tx=em.getTransaction();
          tx.begin();
          
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
                if (em != null) {
                    em.close();
                }
         }
    }
}





