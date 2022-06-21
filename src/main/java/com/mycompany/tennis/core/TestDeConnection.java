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
           
             PreparedStatement preparedStatement=conn.prepareStatement("SELECT NOM,PRENOM, ID FROM JOUEUR WHERE ID=?");
             long identifiant=43L;
             preparedStatement.setLong(1, identifiant);
           
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                final String nom=rs.getString("NOM");
                final String prenom=rs.getString("PRENOM");
                final Long id =rs.getLong("ID");
               System.out.println("Le joueur ou la joueuse représenté(e) par le numéro "+id+" est "+prenom+" "+nom);
            }
            else{
               System.out.println("la joueuse représenté(e) par le numéro d'ID = 145 bn'exiate ");
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