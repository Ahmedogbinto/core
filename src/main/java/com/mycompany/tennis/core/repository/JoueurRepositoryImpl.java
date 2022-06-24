/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.entity.Joueur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author User
 */
public class JoueurRepositoryImpl {
    
  public void createJoueur(Joueur joueur){
       Connection conn = null;
        try {  
            BasicDataSource dataSource=new BasicDataSource();
            
            dataSource.setInitialSize(5);
            
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("COURSDB");
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("INSERT INTO JOUEUR(NOM, PRENOM, SEXE) VALUES(?,?,?)");
                    
             // on ne prepare pas ID parce qu il est automatiquement cree par la BD  
             preparedStatement.setString(1,joueur.getNom());
             preparedStatement.setString(2,joueur.getPrenom());
             preparedStatement.setString(3,joueur.getSexe().toString());
             
             preparedStatement.executeUpdate(); // executeUpdate est le même pour la methode create et update
     
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
  
  
  public void UpdateJoueur(Joueur joueur){
       Connection conn = null;
        try {  
            BasicDataSource dataSource=new BasicDataSource();
            
            dataSource.setInitialSize(5);
            
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("COURSDB");
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? ) WHERE ID=?");
             
               
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
 
  
  
  public void DeleteJoueur(Joueur joueur){
       Connection conn = null;
        try {  
            BasicDataSource dataSource=new BasicDataSource();
            
            dataSource.setInitialSize(5);
            
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("COURSDB");
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("DELETE JOUEUR WHERE ID=?");
             
             //Je n'ai plus qu'un seul parametre a valoriser et c'est id 
             preparedStatement.setLong(1,joueur.getId());
             
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
            BasicDataSource dataSource=new BasicDataSource();
            
            dataSource.setInitialSize(5);
            
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("COURSDB");
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM JOUEUR WHERE ID=?");
             
             //Je n'ai plus qu'un seul parametre a valoriser et c'est id 
             preparedStatement.setLong(1,id);
             
            ResultSet rs =  preparedStatement.executeQuery(); // ici c'est un read(Une lecture, donc c'est executeQuery.
             if (rs.next()){
                 
               joueur = new Joueur();
               
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
            BasicDataSource dataSource=new BasicDataSource();
            
            dataSource.setInitialSize(5);
            
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("COURSDB");
            
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM JOUEUR WHERE ID=?");
             
             
            ResultSet rs =  preparedStatement.executeQuery(); // ici c'est un read(Une lecture, donc c'est executeQuery.
            if(rs.next()){
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
