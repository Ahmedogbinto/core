/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.Controller;

import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.ScoreService;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ScoreController {
    private ScoreService scoreService;

    public ScoreController() {
        this.scoreService = new ScoreService();
    }

   
    
    public void afficherDetailsScore(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du score dont vous voulez affichez les informations");
        long identifiant=sc.nextLong();   
        Score scoreRecupere=scoreService.getScore(identifiant);
        
        System.out.println("Le score que vous aviez sélectionné etait de: ");
        System.out.println(+scoreRecupere.getSet1());
        System.out.println(+scoreRecupere.getSet2());
       
        if(scoreRecupere.getSet3()!=null){
          System.out.println(+scoreRecupere.getSet3());   
        }
        if(scoreRecupere.getSet4()!=null){
          System.out.println(+scoreRecupere.getSet4());   
        }
        if(scoreRecupere.getSet5()!=null){
          System.out.println(+scoreRecupere.getSet5());   
        }
 
    }
    
}
