/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.Dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ressourceUtil.EntityManagerHolder;
import ressourceUtil.HibernateUtil;


/**
 *
 * @author User
 */
public class JoueurService {
    private JoueurRepositoryImpl joueurRepository;
    
    public JoueurService(){
        this.joueurRepository=new JoueurRepositoryImpl();
    }
    
    public void createNouveauJoueur(Joueur joueur){
        Session session = null;
        Transaction tx = null;
        
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tennis-unit");
            
            
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.createJoueur(joueur);
            tx.commit();

            System.out.println("Le joueur a bien été créé");
            System.out.println("L'identifiant du joueur créé est " + joueur.getId());
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
    
   
    public Joueur getJoueur(Long id){
        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          joueur = joueurRepository.getById(id);
          tx.commit();
          
          System.out.println("Le joueur a bien lu");  
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
        return joueur; 
    }
    
         
    public void renommer(Long id, String nouveauNom){
        Joueur joueur = getJoueur(id); // Objet non persistant
        
        Session session = null;
        Transaction tx = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          
          joueur.setNom(nouveauNom);
          Joueur joueur2 = (Joueur)session.merge(joueur);
          tx.commit();
          
          System.out.println("Le joueur a bien été modifier dans la base de donnees");  
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
    
    public void changerSexe(Long id, Character nouveauSexe){
        
        Joueur joueur = getJoueur(id); // Objet non persistant
        
        Session session = null;
        Transaction tx = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          
          joueur.setSexe(nouveauSexe);
          Joueur joueur2 = (Joueur)session.merge(joueur);
          tx.commit();
          
          System.out.println("Le joueur a bien été modifier dans la base de donnees");  
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
    
    public void delete(Long id){
        
        Session session = null;
        Transaction tx = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          
          joueurRepository.deleteJoueur(id);
          tx.commit();
          
          System.out.println("Le joueur a bien été supprimé de la base de donnees");  
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
     public List<JoueurDto> getListeJoueurs(char sexe){
        EntityManager em =null;
        EntityTransaction tx = null;
        List<JoueurDto> joueursDto = new ArrayList<>();
        
        try{
          em = EntityManagerHolder.getCurrentEntityManager();
          tx=em.getTransaction();
          tx.begin();
          
          List<Joueur> joueurs = joueurRepository.lister(sexe);
          for(Joueur joueur: joueurs){
              final JoueurDto joueurDto = new JoueurDto();
              joueurDto.setId(joueur.getId());
              joueurDto.setNom(joueur.getNom());
              joueurDto.setPrenom(joueur.getPrenom());
              joueurDto.setSexe(joueur.getSexe());
              
              joueursDto.add(joueurDto);  
          }
          
          tx.commit();
          
          System.out.println("La liste des joueurs est : ");  
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
        
       return joueursDto;
     }
}


     
    

