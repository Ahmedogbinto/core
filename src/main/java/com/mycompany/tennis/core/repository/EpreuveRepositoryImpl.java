/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.entity.Epreuve;
import org.hibernate.Session;
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
    
}
