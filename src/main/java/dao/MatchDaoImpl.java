/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.sql.DataSource;

/**
 *
 * @author User
 */
public class MatchDaoImpl {
    
     public void createMatchWithScore(Match match){
         Connection conn = null;
        try { 
           // Pas besoin de spécifier que dataSource est une BasicDataSource, puis qu'il avait déja été spécifié plus haut.
           DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
            
            conn=dataSource.getConnection();
            conn.setAutoCommit(false);
  
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
          
             PreparedStatement preparedStatement2=conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR(ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
             
              // Collage de la methode create() de ScoreRepositoryImpl();
             // on ne prepare pas ID parce qu il est automatiquement cree par la BD  => On l'a défini en un second temps avec RETURN_GENERATED_KEYS
             
             preparedStatement2.setLong(1,match.getScore().getMatch().getId());
             preparedStatement2.setByte(2,match.getScore().getSet1());
             preparedStatement2.setByte(3,match.getScore().getSet2());
             
             if(match.getScore().getSet3()==null){
                 preparedStatement2.setNull(4,Types.TINYINT);
             }
             else{
                 preparedStatement2.setByte(4,match.getScore().getSet3()); 
             }
            
             if(match.getScore().getSet4()==null){
                 preparedStatement2.setNull(5,Types.TINYINT);
             }
             else{
                  preparedStatement2.setByte(5,match.getScore().getSet4());
             }
            
             if(match.getScore().getSet5()==null){
                 preparedStatement2.setNull(6,Types.TINYINT);
             }
             else{
                  preparedStatement2.setByte(6,match.getScore().getSet5());
             }
             
             preparedStatement2.executeUpdate();                          //ExecuteUpdate est le même pour la methode create et update
             ResultSet rs2=preparedStatement2.getGeneratedKeys();         //Recuperation dune cle, il peut sagir de plusieur, ici nous voulons recuprer l'ID
             
             if (rs2.next()){
              match.getScore().setId(rs.getLong(1));                             //  Definition de l'ID joueur récupérer
             }
             conn.commit();
              System.out.println("Le score a bien été créé");
              System.out.println("L'identifiant du score créé est "+match.getScore().getId());
            
              System.out.println("Le match a bien été créé");
              System.out.println("L'identifiant du match créé est "+match.getId());
            
              
        } catch (SQLException e) {
            e.printStackTrace();
            try{
               if(conn!=null) conn.rollback(); // inutille de faire un conn.rollback(); pourquoi: puisque la connection n'etait pas en autoCommit();
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
     
}
