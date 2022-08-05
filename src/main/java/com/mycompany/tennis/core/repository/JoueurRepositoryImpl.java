/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import ressourceUtil.HibernateUtil;
import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author User
 */
public class JoueurRepositoryImpl {

    public void createJoueur(Joueur joueur) {
            Session session;
            
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(joueur);   
        } 
    

    public void updateJoueur(Joueur joueur) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getId());

            preparedStatement.executeUpdate(); // executeUpdate est le même pour la methode create et update

            System.out.println("Le joueur a bien été modifié");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteJoueur(Long id) {
    Joueur joueur = getById(id);
    
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.delete(joueur);
    
        }
    

//--------------------------------------------------------------------------------------------------------------------
    public Joueur getById(Long id) {
        Joueur joueur = null;
        Session session = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
        joueur = session.get(Joueur.class, id);

        System.out.println("Le joueur est bien affiché(lu)");
    
        return joueur;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    public List<Joueur> lister() {
       

        Session session = HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
        Query<Joueur> query = session.createQuery("select j from Joueur j", Joueur.class);
       
        List<Joueur> joueurs = query.getResultList();
        
        System.out.println("Les joueurs ont été lus et affichés");
    
        return joueurs;
    }
}