/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.Dto;

/**
 *
 * @author User
 */
public class MatchDto{
    private long id;
    private JoueurDto vainqueur;    
    private JoueurDto finaliste;
    private EpreuveFullDto epreuve;
    private ScoreFullDto score;

    
    public EpreuveFullDto getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(EpreuveFullDto epreuve) {
        this.epreuve = epreuve;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public JoueurDto getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(JoueurDto vainqueur) {
        this.vainqueur = vainqueur;
    }

    public JoueurDto getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(JoueurDto finaliste) {
        this.finaliste = finaliste;
    }
    
    public ScoreFullDto getScore() {
        return score;
    }

    public void setScore(ScoreFullDto score) {
        this.score = score;
    }
    
            
}
