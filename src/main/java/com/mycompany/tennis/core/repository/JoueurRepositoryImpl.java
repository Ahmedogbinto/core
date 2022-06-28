/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author User
 */
public class JoueurRepositoryImpl {
    
  public void createJoueur(Joueur joueur){
       Connection conn = null;
        try { 
           // Pas besoin de spécifier que dataSource est une BasicDataSource, puis qu'il avait déja été spécifié plus haut.
           DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("INSERT INTO JOUEUR(NOM, PRENOM, SEXE) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
             
             // on ne prepare pas ID parce qu il est automatiquement cree par la BD  => On l'a défini en un second temps avec RETURN_GENERATED_KEYS
             preparedStatement.setString(1,joueur.getNom());
             preparedStatement.setString(2,joueur.getPrenom());
             preparedStatement.setString(3,joueur.getSexe().toString());
             
             preparedStatement.executeUpdate();                         //ExecuteUpdate est le même pour la methode create et update
             ResultSet rs=preparedStatement.getGeneratedKeys();        //Recuperation dune cle, il peut sagir de plusieur, ici nous voulons recuprer l'ID
             
             if (rs.next()){
              joueur.setId(rs.getLong(1));                         //  Definition de l'ID joueur récupérer
             }
             
             System.out.println("Le joueur a bien été créé");
            
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
  
  
  public void updateJoueur(Joueur joueur){
       Connection conn = null;
        try {  
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");
             
               
             preparedStatement.setString(1,joueur.getNom());
             preparedStatement.setString(2,joueur.getPrenom());
             preparedStatement.setString(3,joueur.getSexe().toString());
             preparedStatement.setLong(4,joueur.getId());
             
             preparedStatement.executeUpdate(); // executeUpdate est le même pour la methode create et update
     
             System.out.println("Le joueur a bien été modifié");
            
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

  
  public void deleteJoueur(Long id){
       Connection conn = null;
        try {  
           DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");
             
             //Je n'ai plus qu'un seul parametre a valoriser et c'est id 
             preparedStatement.setLong(1,id);
             
             preparedStatement.executeUpdate(); // executeUpdate est le même pour la methode create et update
     
             System.out.println("Le joueur a bien été supprimé");
            
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

  
  public Joueur getById(Long id){
       Connection conn = null;
       Joueur joueur = null; // Attention, une colonne dans la base de donn/es ne peut être nulle
        try {  
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM JOUEUR WHERE ID=?");
             
             //Je n'ai plus qu'un seul parametre a valoriser et c'est id 
             preparedStatement.setLong(1,id);
             
            ResultSet rs =  preparedStatement.executeQuery(); // ici c'est un read(Une lecture, donc c'est executeQuery.
             if (rs.next()){
                 
               joueur = new Joueur();
               joueur.setId(id);
               joueur.setNom(rs.getString("NOM"));
               joueur.setPrenom(rs.getString("PRENOM"));
               joueur.setSexe(rs.getString("SEXE").charAt(0));
                
            }
            
             System.out.println("Le joueur est bien affiché(lu)");   
        }
        catch (SQLException e) {
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
        return joueur; // return pourrait potentielement retourner null, dans le cas ou l'identifiant passé en parametre ne correspond a aucun joueur de la base données
    }
  
  
    public  List<Joueur> lister(){
       Connection conn = null;
       List<Joueur> listDeJoueurs=new ArrayList();
        try {  
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
           
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("SELECT ID, NOM, PRENOM, SEXE FROM JOUEUR");
             
             
            ResultSet rs =  preparedStatement.executeQuery(); // ici c'est un read(Une lecture, donc c'est executeQuery.
           while(rs.next()){
               Joueur joueur = new Joueur();
               
               joueur.setId(rs.getLong("ID"));
               joueur.setNom(rs.getString("NOM"));
               joueur.setPrenom(rs.getString("PRENOM"));
               joueur.setSexe(rs.getString("SEXE").charAt(0));
               
               listDeJoueurs.add(joueur);
            }
            
             System.out.println("La liste des joueurs est bien affiché(lus)");   
        }
        catch (SQLException e) {
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
        return listDeJoueurs; // return pourrait potentielement retourner null, dans le cas ou l'identifiant passé en parametre ne correspond a aucun joueur de la base données
    }
}
