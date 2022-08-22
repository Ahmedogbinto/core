package com.mycompany.tennis.core.entity;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Score;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-08-22T17:06:47")
@StaticMetamodel(Match.class)
public class Match_ { 

    public static volatile SingularAttribute<Match, Joueur> vainqueur;
    public static volatile SingularAttribute<Match, Score> score;
    public static volatile SingularAttribute<Match, Joueur> finaliste;
    public static volatile SingularAttribute<Match, Long> id;
    public static volatile SingularAttribute<Match, Epreuve> epreuve;

}