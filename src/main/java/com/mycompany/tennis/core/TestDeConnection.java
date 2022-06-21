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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4 
            //Class.forName(DRIVER_CLASS_NAME);
            
            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false","COURSDB","COURSDB");
           
            Statement statement = conn.createStatement();
            ResultSet rs=statement.executeQuery("SELECT NOM,PRENOM, ID FROM JOUEUR WHERE ID=128");
            if(rs.next()){
                final String nom=rs.getString("NOM");
                final String prenom=rs.getString("PRENOM");
                final Long id =rs.getLong("ID");
               System.out.println("Le joueur ou la joueuse repr�sent�(e) par le num�ro "+id+" est "+prenom+" "+nom);
            }
            else{
               System.out.println("la joueuse repr�sent�(e) par le num�ro d'ID = 145 bn'exiate ");
                    }
            
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