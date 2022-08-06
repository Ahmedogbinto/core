/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.Dto.EpreuveFullDto;
import com.mycompany.tennis.core.Dto.EpreuveLightDto;
import com.mycompany.tennis.core.Dto.JoueurDto;
import com.mycompany.tennis.core.Dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ressourceUtil.HibernateUtil;

/**
 *
 * @author User
 */
public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }
    
    public EpreuveFullDto getEpreuveDetaillee(Long id){
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveFullDto dto = null;        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          epreuve = epreuveRepository.getById(id);
       
          dto = new EpreuveFullDto();
          dto.setId(epreuve.getId());
          dto.setAnnee(epreuve.getAnnee());
          dto.setTypeEpreuve(epreuve.getTypeEpreuve());
   
          TournoiDto tournoiDto = new TournoiDto();
          tournoiDto.setId(epreuve.getTournoi().getId());
          tournoiDto.setNom(epreuve.getTournoi().getNom());
          tournoiDto.setCode(epreuve.getTournoi().getCode());
          dto.setTournoi(tournoiDto); 
          
          dto.setParticipants(new HashSet<>());                   // valorisation de la collection au depart du fait quelle soit null
          for(Joueur joueur:epreuve.getParticipants()){
           final JoueurDto joueurDto = new JoueurDto();           // Pour chaque element (conversion de collection de joueur en collection de joueurDto) je vais creer un nouveau joueurDto
          
           joueurDto.setId(joueur.getId());
           joueurDto.setNom(joueur.getNom());
           joueurDto.setPrenom(joueur.getPrenom());
           joueurDto.setSexe(joueur.getSexe());
           
           dto.getParticipants().add(joueurDto);
          }
          
          tx.commit();
        }
        
        catch (Exception e) {
            e.printStackTrace();
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }finally {
                if (session != null) {
                    session.close();
                }
         }
        return dto;      
    }
    
     public EpreuveLightDto getEpreuveSansTournoi(Long id){
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveLightDto dto = null;
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          epreuve = epreuveRepository.getById(id);
         
          dto = new EpreuveLightDto();
          dto.setId(epreuve.getId());
          dto.setAnnee(epreuve.getAnnee());
          dto.setTypeEpreuve(epreuve.getTypeEpreuve());
           tx.commit();
           
        }
        catch (Exception e) {
            e.printStackTrace();
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }finally {
                if (session != null) {
                    session.close();
                }
         }
        return dto; 
        
    }
    
    public List<EpreuveFullDto> getListEpreuve(String codeTournoi){
        Session session = null;
        Transaction tx = null;
        List<EpreuveFullDto> listDto = new ArrayList<>();
        
        try{
          session=HibernateUtil.getSessionFactory().getCurrentSession(); // C'est grace à cette objet cession que l'on pourra faire du Read, create, delete, update. 
          tx=session.beginTransaction();
          
          List<Epreuve> epreuves = epreuveRepository.lister(codeTournoi);
          for(Epreuve epreuve: epreuves){
              final EpreuveFullDto epreuveDto = new EpreuveFullDto();
              epreuveDto.setId(epreuve.getId());
              epreuveDto.setAnnee(epreuve.getAnnee());
              epreuveDto.setTypeEpreuve(epreuve.getTypeEpreuve());
              TournoiDto tournoiDto = new TournoiDto();
              tournoiDto.setId(epreuve.getTournoi().getId());
              tournoiDto.setCode(epreuve.getTournoi().getCode());
              tournoiDto.setNom(epreuve.getTournoi().getNom());
              
              epreuveDto.setTournoi(tournoiDto);
              listDto.add(epreuveDto);    
          }
              tx.commit();
              System.out.println("La liste des epreuves du tournoi choisi est: ");
        }
        catch (Exception e) {
            e.printStackTrace();
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }finally {
                if (session != null) {
                    session.close();
                }
           }
        
       return listDto;     
    }
   
    
}
