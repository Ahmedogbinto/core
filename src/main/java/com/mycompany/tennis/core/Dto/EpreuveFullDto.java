/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.Dto;

import com.mycompany.tennis.core.entity.Tournoi;
import java.util.Set;

/**
 *
 * @author User
 */
public class EpreuveFullDto {
    
    private Long id;
    private Short annee;
    private Tournoi tournoi;
    private Character TypeEpreuve;
    private Set<JoueurDto> participants;

    public Set<JoueurDto> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<JoueurDto> participants) {
        this.participants = participants;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }
    
    public Character getTypeEpreuve() {
        return TypeEpreuve;
    }

    public void setTypeEpreuve(Character TypeEpreuve) {
        this.TypeEpreuve = TypeEpreuve;
    }
    
}
