/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.Controller;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.service.EpreuveService;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class EpreuveController {
    private EpreuveService epreuveService;

    public EpreuveController() {
        this.epreuveService = new EpreuveService();
    }

     public void afficherEpreuve(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez affichez les informations");
        long identifiant=sc.nextLong();   
        Epreuve epreuve=epreuveService.getEpreuveSansTournoi(identifiant);
        System.out.println("Le nom de l'epreuve que vous aviez demande s'est déroulé en "+epreuve.getAnnee()+" iL s'agissait d'une epreuve de type "+epreuve.getTypepeEpreuve() );
                
         }
     
     public void afficheDetailsEpreuve(){
         Scanner sc=new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez affichez les informations");
        long identifiant=sc.nextLong();   
        Epreuve epreuve=epreuveService.getEpreuveAvecTournoi(identifiant);
        System.out.println("Le nom de l'epreuve que vous aviez demande s'est déroulé en "+epreuve.getAnnee()+" il s'agissait du tournoi"+epreuve.getTournoi().getNom());
         }
}
