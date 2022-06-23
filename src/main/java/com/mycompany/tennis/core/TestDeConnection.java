/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core;
/**
 *
 * @author User
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {  
            BasicDataSource dataSource=new BasicDataSource();
            
            dataSource.setInitialSize(5);
            
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("COURSDB");
            
            conn=dataSource.getConnection();
            
            //MySQL driver MySQL Connector
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false","COURSDB","COURSDB");
            conn.setAutoCommit(false);
            
             PreparedStatement preparedStatement=conn.prepareStatement("INSERT INTO JOUEUR(NOM, PRENOM, SEXE) VALUES(?,?,?)");
             
             String nom="Capriati";
             String prenom="Jennifer";
             String sexe="F";
                         
             preparedStatement.setString(1,nom);
             preparedStatement.setString(2,prenom);
             preparedStatement.setString(3,sexe);
             
             preparedStatement.executeUpdate();
             
             nom="Johannson";
             prenom="Thomas";
             sexe="H";
             
             preparedStatement.setString(1,nom);
             preparedStatement.setString(2,prenom);
             preparedStatement.setString(3,sexe);
             
             preparedStatement.executeUpdate();
             conn.commit();
             System.out.println("success");
            
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
}