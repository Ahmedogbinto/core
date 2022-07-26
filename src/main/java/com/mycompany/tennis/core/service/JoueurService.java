/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
    
    
}
