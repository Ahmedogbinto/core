/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.Controller;

import com.mycompany.tennis.core.Dto.EpreuveFullDto;
import com.mycompany.tennis.core.Dto.JoueurDto;
import com.mycompany.tennis.core.Dto.MatchDto;
import com.mycompany.tennis.core.Dto.ScoreFullDto;
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
    
    public void ajouterMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'dentifiant de l'epreuve");
        long epreuveId = scanner.nextLong();
        scanner.nextLine();
        
        System.out.println("Quel est l'dentifiant du vainqueur");
        long vainqueurId = scanner.nextLong();
        scanner.nextLine();
        
        System.out.println("Quel est l'dentifiant du finaliste");
        long finalisteId = scanner.nextLong();
        scanner.nextLine();
        
        MatchDto matchDto = new MatchDto();                      // Voici le match a ajouter dans dans la Bd, il aura une epreuve, 
        matchDto.setEpreuve(new EpreuveFullDto());               // Debut d'ajout d'une epreuve, un finaliste et un vainqueur vide)
        matchDto.getEpreuve().setId(epreuveId);
        
        matchDto.setVainqueur(new JoueurDto());
        matchDto.getVainqueur().setId(vainqueurId);
        
        matchDto.setFinaliste(new JoueurDto());
        matchDto.getFinaliste().setId(finalisteId);
        
        System.out.println("Quel est la valeur du 1er set?");      // recuperation des 5 sets meme si le match fini en 2 sets
        byte set1 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 2eme set?");
        byte set2 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 3eme set?");
        byte set3 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 4eme set?");
        byte set4 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 5eme set?");
        byte set5 = scanner.nextByte();
        scanner.nextLine();
        
        ScoreFullDto scoreDto = new ScoreFullDto();
        scoreDto.setSet1(set1);
        scoreDto.setSet1(set2);
        scoreDto.setSet1(set3);
        scoreDto.setSet1(set4);
        scoreDto.setSet1(set5);
        
        matchDto.setScore(scoreDto);
       scoreDto.setMatch(matchDto);
        
        matchService.createMatch(matchDto);
    
}
    
    public void tapisVert(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'dentifiant du match dont vous voudriez annulez le score");
        long identifiant = scanner.nextLong();
        matchService.tapisVert(identifiant);
    }
    
    public void afficherDetailsMatch(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du matct dont vous voulez affichez les informations");
        long identifiant=scanner.nextLong();   
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
    
    public void supprimerMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'dentifiant du match que vous voudriez supprimer");
        long identifiant = scanner.nextLong();
        matchService.deleteMatch(identifiant);
        
    }
    
}
