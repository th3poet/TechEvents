/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author thepoet
 */
public class Users {
    
    private int ID_USERS;
    private String FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,PROFILE_PHOTO;

    public Users() {
    }

    public Users(int ID_USERS, String FIRST_NAME, String LAST_NAME, String EMAIL, String PASSWORD, String PROFILE_PHOTO) {
        this.ID_USERS = ID_USERS;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.PROFILE_PHOTO = PROFILE_PHOTO;
    }
    
    public Users(String FIRST_NAME, String LAST_NAME, String EMAIL, String PASSWORD, String PROFILE_PHOTO) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.PROFILE_PHOTO = PROFILE_PHOTO;
    }
    
    public Users(String EMAIL, String PASSWORD) {
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
    }
    
    public int getID_USERS() {
        return ID_USERS;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getPROFILE_PHOTO() {
        return PROFILE_PHOTO;
    }

    public void setID_USERS(int ID_USERS) {
        this.ID_USERS = ID_USERS;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setPROFILE_PHOTO(String PROFILE_PHOTO) {
        this.PROFILE_PHOTO = PROFILE_PHOTO;
    }
    
    
    @Override
    public String toString() {
        return "Utilisateur{" + "Nom =" + LAST_NAME + ", Prénom=" + FIRST_NAME + ", Email" + EMAIL + ", Mot de passe =" + PASSWORD + ", Photo de Profile=" + PROFILE_PHOTO + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.ID_USERS;
        hash = 53 * hash + Objects.hashCode(this.EMAIL);
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
        final Users other = (Users) obj;
        if (this.ID_USERS != other.ID_USERS) {
            return false;
        }
        if (!Objects.equals(this.FIRST_NAME, other.FIRST_NAME)) {
            return false;
        }
        if (!Objects.equals(this.LAST_NAME, other.LAST_NAME)) {
            return false;
        }
        if (!Objects.equals(this.EMAIL, other.EMAIL)) {
            return false;
        }
        if (!Objects.equals(this.PASSWORD, other.PASSWORD)) {
            return false;
        }
        return true;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        
        return new Users(this.ID_USERS, this.FIRST_NAME, this.LAST_NAME, this.EMAIL, this.PASSWORD, this.PROFILE_PHOTO);
        
    }
}
    
