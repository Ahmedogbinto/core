/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.entity.Epreuve;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ressourceUtil.HibernateUtil;


/**
 *
 * @author User
 */
public class EpreuveRepositoryImpl {
    
    
     public Epreuve getById(Long id) {
        Epreuve epreuve = null;
        Session session = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
        epreuve = session.get(Epreuve.class, id);

        System.out.println("L'épreuve est affiché");
    
        return epreuve;
     }
    
     public List<Epreuve> lister(String codeTournoi){
         Session session = HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
         Query<Epreuve> query = session.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?0", Epreuve.class);  // pas besoin d'être explicite sur la requête puisque hibernate, Il connait la relation manyToOne qui existe entre Epreuve.  
        
        query.setParameter(0, codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        
        
        System.out.println("Les epreuves ont été lus et affichés");
    
        return epreuves;
         
     }
}
