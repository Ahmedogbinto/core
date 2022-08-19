package com.mycompany.tennis.core.entity;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-08-19T16:05:30")
@StaticMetamodel(Epreuve.class)
public class Epreuve_ { 

    public static volatile SingularAttribute<Epreuve, Tournoi> tournoi;
    public static volatile SingularAttribute<Epreuve, Character> TypeEpreuve;
    public static volatile SingularAttribute<Epreuve, Long> id;
    public static volatile SingularAttribute<Epreuve, Short> annee;
    public static volatile SetAttribute<Epreuve, Joueur> participants;

}