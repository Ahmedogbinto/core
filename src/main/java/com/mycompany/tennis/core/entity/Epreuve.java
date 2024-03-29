/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author User
 */
@Entity
public class Epreuve implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Short annee;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TOURNOI")
    private Tournoi tournoi;
    @Column(name="TYPE_EPREUVE")
    private Character TypeEpreuve;
    
    @ManyToMany
    @JoinTable(
            name = "PARTICIPANTS",
            joinColumns={@JoinColumn(name="ID_EPREUVE")},
            inverseJoinColumns={@JoinColumn(name="ID_JOUEUR")}
    )
    private Set<Joueur> participants;

    public Set<Joueur> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Joueur> participants) {
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
