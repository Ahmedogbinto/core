/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
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
    
    public Epreuve getEpreuve(Long id){
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          epreuve = epreuveRepository.getById(id);
            System.out.println("l'identifiant de l'épreuve est "+epreuve.getTournoi().getId());
         System.out.println("La classe de la propriete tournoi est "+epreuve.getTournoi().getClass().getName());
        System.out.println("L'épreuve que vous aviez demandé s'est produit en "+epreuve.getAnnee()+" ; il s'agissait d'une eptruve de type "+epreuve.getTypepeEpreuve()+" du tounoi "+epreuve.getTournoi().getNom());
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
