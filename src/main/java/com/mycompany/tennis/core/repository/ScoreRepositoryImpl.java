/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.sql.DataSource;
import org.hibernate.Session;
import ressourceUtil.HibernateUtil;

/**
 *
 * @author User
 */
public class ScoreRepositoryImpl {
    
    public void createScore(Score score){
       Connection conn = null;
        try { 
           // Pas besoin de spécifier que dataSource est une BasicDataSource, puis qu'il avait déja été spécifié plus haut.
           DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR(ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
             
             // on ne prepare pas ID parce qu il est automatiquement cree par la BD  => On l'a défini en un second temps avec RETURN_GENERATED_KEYS
             
             preparedStatement.setLong(1,score.getMatch().getId());
             preparedStatement.setByte(2,score.getSet1());
             preparedStatement.setByte(3,score.getSet2());
             
             if(score.getSet3()==null){
                 preparedStatement.setNull(4,Types.TINYINT);
             }
             else{
                 preparedStatement.setByte(4,score.getSet3()); 
             }
            
             if(score.getSet4()==null){
                 preparedStatement.setNull(5,Types.TINYINT);
             }
             else{
                  preparedStatement.setByte(5,score.getSet4());
             }
            
             if(score.getSet5()==null){
                 preparedStatement.setNull(6,Types.TINYINT);
             }
             else{
                  preparedStatement.setByte(6,score.getSet5());
             }
             
             preparedStatement.executeUpdate();                          //ExecuteUpdate est le même pour la methode create et update
             ResultSet rs=preparedStatement.getGeneratedKeys();         //Recuperation dune cle, il peut sagir de plusieur, ici nous voulons recuprer l'ID
             
             if (rs.next()){
              score.setId(rs.getLong(1));                             //  Definition de l'ID joueur récupérer
             }
             
              System.out.println("Le score a bien été créé");
              System.out.println("L'identifiant du score créé est "+score.getId());
            
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
    
     public Score getById(Long id) {
        Score score = null;
        Session session = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
        score = session.get(Score.class, id);

        System.out.println("Le score a bien été affiché(lu)");
    
        return score;
    }
     
    public void delete(Long id){
      Session session = HibernateUtil.getSessionFactory().getCurrentSession();
      Score score=session.get(Score.class,id);
      session.delete(score);
      
      System.out.println("Le score a bien ete supprime");
    }
  
    
}
