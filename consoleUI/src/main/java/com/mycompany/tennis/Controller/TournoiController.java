/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.Controller;

import com.mycompany.tennis.core.Dto.TournoiDto;
import com.mycompany.tennis.core.service.TournoiService;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class TournoiController {
    private TournoiService tournoiService;

    
    public TournoiController(){
        this.tournoiService=new TournoiService();
    }
       
    public void afficherDetailsTournoi(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du tournoi dont vous voulez affichez les informations");
        Long identifiant=sc.nextLong();   
        TournoiDto tournoiRecupere=tournoiService.getTournoi(identifiant);
        
        System.out.println("Le Tounoi  que vous aviez sélectionné s'appelle: "+tournoiRecupere.getId()+" "+tournoiRecupere.getNom()+" "+tournoiRecupere.getCode());
    }
     
  public void creerTournoi(){
        TournoiDto tournoiCree = new TournoiDto();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer le nom du nouveau tournoi :");
        String nom = sc.nextLine();
        System.out.println("Entrer le code du nouveau tournoi:");
        String code = sc.nextLine();
         
         tournoiCree.setNom(nom);
         tournoiCree.setCode(code);
         
         tournoiService.create(tournoiCree);
         System.out.println("Le tournoi a été créé et ajouter dans la base de données");
}
  
  public void supprimerTournoi(){
      Scanner scanner = new Scanner(System.in);
        
        System.out.println("Quel est l'identifiant du tournoi dont vous vouidriez supprimez");
        Long identifiant= scanner.nextLong();
      
      tournoiService.delete(identifiant);
      
  }
  
}
