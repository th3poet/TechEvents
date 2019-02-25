/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author toshiba.pc
 */
public class Participation {
    
    int idParticipation ; 
    int idEvenement ; 
    int iduser;

    public Participation(int idParticipation,int idEvenement,int iduser) {
        this.idParticipation = idParticipation;
        this.idEvenement = idEvenement;
        this.iduser=iduser;
    }

   

    public Participation() {
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

  
    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idParticipation;
      
        hash = 89 * hash + this.idEvenement;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Participation other = (Participation) obj;
        if (this.idParticipation != other.idParticipation) {
            return false;
        }
        
        if (this.idEvenement != other.idEvenement) {
            return false;
        }
        return true;
    }
    
    
    
}
