/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Tournoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.hibernate.Session;
import ressourceUtil.HibernateUtil;

/**
 *
 * @author User
 */
public class MatchRepositoryImpl {
    
  public void createMatch(Match match){
       Connection conn = null;
        try { 
           // Pas besoin de spécifier que dataSource est une BasicDataSource, puis qu'il avait déja été spécifié plus haut.
           DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
             
             // on ne prepare pas ID parce qu il est automatiquement cree par la BD  => On l'a défini en un second temps avec RETURN_GENERATED_KEYS
             preparedStatement.setLong(1,match.getEpreuve().getId());
             preparedStatement.setLong(2,match.getVainqueur().getId());
             preparedStatement.setLong(3,match.getFinaliste().getId());
             
             preparedStatement.executeUpdate();                         //ExecuteUpdate est le même pour la methode create et update
             ResultSet rs=preparedStatement.getGeneratedKeys();        //Recuperation dune cle, il peut sagir de plusieur, ici nous voulons recuprer l'ID
             if (rs.next()){
              match.setId(rs.getLong(1));                             //  Definition de l'ID joueur récupérer
             }
             
              System.out.println("Le match a bien été créé");
              System.out.println("L'identifiant du match créé est "+match.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            try{
               if(conn!=null) conn.rollback();
            }
            catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
  
  public Match getById(Long id){
        Session session = null;
        Match match = null;
             session=HibernateUtil.getSessionFactory().openSession();
             match=session.get(Match.class,id);
             
             System.out.println("le Match à afficher est:");
        
        return match;
        
    }
}
  
  