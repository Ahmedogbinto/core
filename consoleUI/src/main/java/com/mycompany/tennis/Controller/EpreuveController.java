/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.Controller;

import com.mycompany.tennis.core.Dto.EpreuveFullDto;
import com.mycompany.tennis.core.Dto.EpreuveLightDto;
import com.mycompany.tennis.core.Dto.JoueurDto;
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
        EpreuveLightDto epreuve=epreuveService.getEpreuveSansTournoi(identifiant);
        
        System.out.println("Le nom de l'epreuve que vous aviez demande s'est déroulé en "+epreuve.getAnnee()+" iL s'agissait d'une epreuve de type "+epreuve.getTypepeEpreuve() );
                
         }
     
     public void afficherDetailsEpreuve(){
         Scanner sc=new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez affichez les informations");
        long identifiant=sc.nextLong();   
        EpreuveFullDto epreuve=epreuveService.getEpreuveDetaillee(identifiant);
        
        System.out.println("Le nom du tournoi de cette epreuve etait le: " +epreuve.getTournoi().getNom());
        System.out.println("Le nom des joueurs participants etaient:");
         
        for (JoueurDto joueurDto: epreuve.getParticipants()){
         
            System.out.println(joueurDto.getNom()+" "+joueurDto.getPrenom());
        }
     }
     
     public void afficherListEpreuve(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Quel est le code du tournoi dont vous voudriez les epreuves ");
         String codeTournoi = scanner.nextLine();
         System.out.println("");
         for(EpreuveFullDto epreuveDto: epreuveService.getListEpreuve(codeTournoi)){
             System.out.println(+epreuveDto.getId()+" "+epreuveDto.getAnnee()+" "+epreuveDto.getTypeEpreuve()+" "+epreuveDto.getTournoi().getNom()+" "+epreuveDto.getTournoi().getCode());
         }
         
         
         
     }
     
}
