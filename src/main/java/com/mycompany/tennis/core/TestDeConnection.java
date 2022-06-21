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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {   
            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false","COURSDB","COURSDB");
           
             PreparedStatement preparedStatement=conn.prepareStatement("UPDATE JOUEUR SET NOM=?,PRENOM=? WHERE ID=?");
             long identifiant=24L;
             String nom="Errani";
             String prenom="Sara";
             
             preparedStatement.setString(1,nom);
             preparedStatement.setString(2,prenom);
             preparedStatement.setLong(3,identifiant);
             
           
            int nbNombesEnregistrementsModifes=preparedStatement.executeUpdate();
           
               System.out.println("nbNombesEnregistrementsModifes est "+nbNombesEnregistrementsModifes);
            
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
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