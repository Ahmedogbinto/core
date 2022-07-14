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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author User
 */
public class JoueurRepositoryImpl {
    
  public void createJoueur(Joueur joueur){
      Session session = null;
      Transaction tx = null;
        try { 
            session=HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            session.persist(joueur);
            tx.commit();
           
            System.out.println("Le joueur a bien �t� cr��");
            System.out.println("L'identifiant du joueur cr�� est "+joueur.getId());
            
        } catch (Exception e) {
            if(tx!=null){
              tx.rollback();
            }
           e.printStackTrace();
            }
        finally {
        
            if (session!=null) {
                session.close();
            }
        } 
    }
  public void renommer(Long id, String nouveauNom){
        Joueur joueur = null;
        Transaction tx = null;
        Session session = null;
      try{
          session=HibernateUtil.getSessionFactory().openSession(); // C'est grace � cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          joueur=session.get(Joueur.class,id);
          joueur.setNom(nouveauNom);
          tx.commit();
          
          System.out.println("Le joueur a �t� bien modifi� dans la base de donn�es");          
        }
         catch (Exception e) {
            if(tx!=null){
              tx.rollback();
            }
           e.printStackTrace();
            }
         finally{
          if(session!=null){
              session.close();
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
             
             preparedStatement.executeUpdate(); // executeUpdate est le m�me pour la methode create et update
     
             System.out.println("Le joueur a bien �t� modifi�");
            
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
             
             preparedStatement.executeUpdate(); // executeUpdate est le m�me pour la methode create et update
     
             System.out.println("Le joueur a bien �t� supprim�");
             
            
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
  
//--------------------------------------------------------------------------------------------------------------------
  
  public Joueur getById(Long id){
      Joueur joueur = null;
      Session session = null;
      try{
          session=HibernateUtil.getSessionFactory().openSession(); // C'est grace � cette objet cession que l'on pourra faire du Read, create, delete, update. 
          joueur=session.get(Joueur.class,id);
          
          System.out.println("Le joueur est bien affich�(lu)");          
        }
      catch(Throwable t){
          t.printStackTrace();
      }
      finally{
          if(session!=null){
              session.close();
          }
      }
      return joueur;
    }
  
  //-----------------------------------------------------------------------------------------------------------------------
   
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
            
             System.out.println("La liste des joueurs est bien affich�(lus)");   
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
        return listDeJoueurs; // return pourrait potentielement retourner null, dans le cas ou l'identifiant pass� en parametre ne correspond a aucun joueur de la base donn�es
    }
}
