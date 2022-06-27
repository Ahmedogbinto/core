/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author User
 */
public class DataSourceProvider {
  private static BasicDataSource singleDataSource;
  
  public static DataSource getSingleDataSourceInstance(){
      if(singleDataSource==null){
            singleDataSource=new BasicDataSource();
            singleDataSource.setInitialSize(5);
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            singleDataSource.setUsername("COURSDB");
            singleDataSource.setPassword("COURSDB");   
            
      }
             return singleDataSource; 
  }
}
