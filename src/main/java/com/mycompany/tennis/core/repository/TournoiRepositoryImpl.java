/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Tournoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ressourceUtil.HibernateUtil;

/**
 *
 * @author User
 */
// Creation du Repository Tournoi avec les operation CRUD
public class TournoiRepositoryImpl {
    
    public void createTournoi(Tournoi tournoi){
            Session session = null;
            Transaction tx = null;
        try{
           session=HibernateUtil.getSessionFactory().openSession();
           tx=session.beginTransaction();
           session.persist(tournoi);
           tx.commit();
             
             System.out.println("Un nouveau tournoi a bien �t� cr�� et ajout�");
             System.out.println("son identifiant est "+tournoi.getId());
        }
        catch(Exception e){
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
    
    public Tournoi getById(Long id){
        Session session = null;
        Tournoi tournoi = null;
        try{
             session=HibernateUtil.getSessionFactory().openSession();
             tournoi=session.get(Tournoi.class,id);
             
             System.out.println("le tournoi � afficher est:");
                 
        }
        catch(Throwable t){
            t.printStackTrace(); 
        } 
        finally {
            try {
                if (session!=null) {
                    session.close();
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }  
        }
        return tournoi;
    }
    
    
    public void updateTournoi(Tournoi tournoi){
        Connection conn = null;
        
        try{
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement("UPDATE TOURNOI SET NOM=?, CODE=?, WHERE ID=?");
           
             preparedStatement.setString(1,tournoi.getNom());
             preparedStatement.setString(2,tournoi.getCode());
             preparedStatement.setLong(4,tournoi.getId());
         
             preparedStatement.executeUpdate(); 

             System.out.println("le tournoi a bien �t� modifi�");
        }
        catch(SQLException e){
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
    
    
    public void deleteTournoi(Long id){
        Tournoi tournoi = getById(id);
    
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.delete(tournoi);
    
    }
    
    
    public  List<Tournoi> lister(){
       Connection conn = null;
       List<Tournoi> listDesTournois=new ArrayList();
       
        try {  
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();
           
            conn=dataSource.getConnection();
  
             PreparedStatement preparedStatement=conn.prepareStatement("SELECT ID, NOM, CODE FROM TOURNOI");
             
             
            ResultSet rs =  preparedStatement.executeQuery(); // ici c'est un read(Une lecture, donc c'est executeQuery.
           while(rs.next()){
               Tournoi tournoi = new Tournoi();
               
               tournoi.setId(rs.getLong("ID"));
               tournoi.setNom(rs.getString("NOM"));
               tournoi.setCode(rs.getString("CODE"));
                
               listDesTournois.add(tournoi);
            }
            
             System.out.println("La liste des tournois est bien affich�(lus)");   
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
        return listDesTournois; // return pourrait potentielement retourner null, dans le cas ou l'identifiant pass� en parametre ne correspond a aucun joueur de la base donn�es
    }
}
