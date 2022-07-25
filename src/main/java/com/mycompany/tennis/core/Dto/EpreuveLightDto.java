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
public class EpreuveLightDto {
    private Long id;
    private Short annee;
    private Character TypeEpreuve;

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
    
    public Character getTypepeEpreuve() {
        return TypeEpreuve;
    }

    public void setTypeEpreuve(Character TypeEpreuve) {
        this.TypeEpreuve = TypeEpreuve;
    }
    
}
