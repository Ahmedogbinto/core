/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.Controller;

import com.mycompany.tennis.core.Dto.MatchDto;
import com.mycompany.tennis.core.service.MatchService;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class MatchController {
    private MatchService  matchService;
    
    public MatchController() {
        this.matchService = new MatchService();
    }
    
    public void afficherDetailsMatch(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du matct dont vous voulez affichez les informations");
        long identifiant=sc.nextLong();   
        MatchDto matchDto =matchService.getMatch(identifiant);
          System.out.println("Il s'agit d'un match de "+matchDto.getEpreuve().getAnnee()+" qui s'est déroulé à "+matchDto.getEpreuve().getTournoi().getNom());
          System.out.println("Le nom et le prenom du vainqueur est "+matchDto.getVainqueur().getPrenom()+" "+matchDto.getVainqueur().getNom());
          System.out.println("Le nom et le prenom du finaliste est "+matchDto.getFinaliste().getPrenom()+" "+matchDto.getFinaliste().getNom());      
         
          System.out.println("Le score  etait de: ");
        System.out.println(+matchDto.getScore().getSet1());
        System.out.println(+matchDto.getScore().getSet2());

        if(matchDto.getScore().getSet3()!=null){
          System.out.println(+matchDto.getScore().getSet3());   
        }
        if(matchDto.getScore().getSet4()!=null){
          System.out.println(+matchDto.getScore().getSet4());   
        }
        if(matchDto.getScore().getSet5()!=null){
          System.out.println(+matchDto.getScore().getSet5());   
        }
    }
    
    
}
