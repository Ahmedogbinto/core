/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author User
 */

@Entity
@Table(name="score_vainqueur")
public class Score implements Serializable {  

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
   @Column(name="ID_MATCH")
   private Long id;
   @Column(name="SET_1")
   private Byte set1;
   @Column(name="SET_2")
   private Byte set2;
   @Column(name="SET_3")
   private Byte set3;
   @Column(name="SET_4")
   private Byte set4;
   @Column(name="SET_5")
   private Byte set5;
   @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
   @JoinColumn(name="ID_MATCH")
   private Match match;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getSet1() {
        return set1;
    }

    public void setSet1(Byte set1) {
        this.set1 = set1;
    }

    public Byte getSet2() {
        return set2;
    }

    public void setSet2(Byte set2) {
        this.set2 = set2;
    }

    public Byte getSet3() {
        return set3;
    }

    public void setSet3(Byte set3) {
        this.set3 = set3;
    }

    public Byte getSet4() {
        return set4;
    }

    public void setSet4(Byte set4) {
        this.set4 = set4;
    }

    public Byte getSet5() {
        return set5;
    }

    public void setSet5(Byte set5) {
        this.set5 = set5;
    }
   
   
            
    
}
