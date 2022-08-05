/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.Controller;

import com.mycompany.tennis.core.Dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class JoueurController {
     private JoueurService joueurService;
    
    public JoueurController(){
        this.joueurService=new JoueurService();
    }
    public void afficherDetailsJoueur(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur dont vous voulez affichez les informations");
        long identifiant=sc.nextLong();   
        Joueur joueurRecupere=joueurService.getJoueur(identifiant);
        
        System.out.println("Le joueur que vous aviez sélectionné s'appelle: "+joueurRecupere.getPrenom()+" "+joueurRecupere.getNom());
    }
    
    public void creerJoueur(){
        Joueur joueurCree = new Joueur();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer le nom du nouveau joueur :");
        String nom = sc.nextLine();
        System.out.println("Entrer le premom du nouveau joueur:");
        String prenom = sc.nextLine();
        System.out.println("Entrer le sexe du nouveau joueur");
         char sexe = sc.nextLine().charAt(0);
         
         joueurCree.setNom(nom);
         joueurCree.setPrenom(prenom);
         joueurCree.setSexe(sexe);
         
         joueurService.createNouveauJoueur(joueurCree);
         System.out.println("Le joueur a été créé et ajouter dans la base de données");
        
    }
    
    public void renommeJoueur(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Quel est l'identifiant du joueur à modifier");
        Long identifiant = scanner.nextLong();
        scanner.nextLine();
        
        System.out.println("Quel est le nouveau nom du joueur");
        String nouveauNom = scanner.nextLine();
        
        joueurService.renommer(identifiant, nouveauNom);

    }
    
    public void ChangerSexeJoueur(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Quel est l'identifiant du joueur dont vous voudriez modifier le sexe");
        Long identifiant = scanner.nextLong();
        
        System.out.println("Quel est le nouverau sexe du joueur");
        scanner.nextLine();
        String nouveauSexe = scanner.nextLine();
        Character sexe=nouveauSexe.charAt(0);
      
        joueurService.changerSexe(identifiant, sexe);
    }
    
    public void SupprimerJoueur(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Quel est l'identifiant du joueur dont vous vouidriez supprimez");
        Long identifiant= scanner.nextLong();
      
        joueurService.delete(identifiant);
    }
            
    public void afficherListeJoueurs(){
        
        for(JoueurDto joueurDto: joueurService.getListeJoueurs()){
            System.out.println(joueurDto.getPrenom()+" "+joueurDto.getNom());
        }
    }
}

