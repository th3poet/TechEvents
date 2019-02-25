/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author toshiba.pc
 */
public class Evenement {
  private int ID_EVENT;
  private int ID_USER ;
  private int ID_ORGANISATEUR;
  private String NOM_EVENT ;
  private String DESC_EVENT;
  private Date dateEvenement;
  private String etat;
  private String duree;
  private String Image;
  private String type;
  private int nbrPlaces ;
  private int prix;
  private static Evenement instance;

    public Evenement(int ID_USER, int ID_ORGANISATEUR, String NOM_EVENT, String DESC_EVENT, Date dateEvenement, String etat, String duree, String Image, String type, int nbrPlaces, int prix) {
        this.ID_USER = ID_USER;
        this.ID_ORGANISATEUR = ID_ORGANISATEUR;
        this.NOM_EVENT = NOM_EVENT;
        this.DESC_EVENT = DESC_EVENT;
        this.dateEvenement = dateEvenement;
        this.etat = etat;
        this.duree = duree;
        this.Image = Image;
        this.type = type;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
    }

    public Evenement(String NOM_EVENT, String DESC_EVENT, Date dateEvenement, String etat, String duree, String Image, String type, int nbrPlaces, int prix) {
        this.NOM_EVENT = NOM_EVENT;
        this.DESC_EVENT = DESC_EVENT;
        this.dateEvenement = dateEvenement;
        this.etat = etat;
        this.duree = duree;
        this.Image = Image;
        this.type = type;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
    }

    public static Evenement getInstance() {
        return instance;
    }

    public static void setInstance(Evenement instance) {
        Evenement.instance = instance;
    }

    
    @Override
    public String toString() {
        return "Evenement{" + "ID_EVENT=" + ID_EVENT + ", ID_USER=" + ID_USER + ", ID_ORGANISATEUR=" + ID_ORGANISATEUR + ", NOM_EVENT=" + NOM_EVENT + ", DESC_EVENT=" + DESC_EVENT + ", dateEvenement=" + dateEvenement + ", etat=" + etat + ", duree=" + duree + ", Image=" + Image + ", type=" + type + ", nbrPlaces=" + nbrPlaces + ", prix=" + prix + '}';
    }

    public int getID_EVENT() {
        return ID_EVENT;
    }

    public void setID_EVENT(int ID_EVENT) {
        this.ID_EVENT = ID_EVENT;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public int getID_ORGANISATEUR() {
        return ID_ORGANISATEUR;
    }

    public void setID_ORGANISATEUR(int ID_ORGANISATEUR) {
        this.ID_ORGANISATEUR = ID_ORGANISATEUR;
    }

    public String getNOM_EVENT() {
        return NOM_EVENT;
    }

    public void setNOM_EVENT(String NOM_EVENT) {
        this.NOM_EVENT = NOM_EVENT;
    }

    public String getDESC_EVENT() {
        return DESC_EVENT;
    }

    public void setDESC_EVENT(String DESC_EVENT) {
        this.DESC_EVENT = DESC_EVENT;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Evenement() {
    }

    

  
  
  
    

    

 
    

    

    
  
}
