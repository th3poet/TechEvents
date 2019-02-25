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
public class Evaluation {
    private int evaluationId;
    private int idUser ; 
    private int idEvenement ; 
    private float noteEvenement;

    public Evaluation(int evaluationId, int idUser, int idEvenement, float noteEvenement) {
        this.evaluationId = evaluationId;
        this.idUser = idUser;
        this.idEvenement = idEvenement;
        this.noteEvenement = noteEvenement;
    }

    public Evaluation(int idUser, int idEvenement, float noteEvenement) {
        this.idUser = idUser;
        this.idEvenement = idEvenement;
        this.noteEvenement = noteEvenement;
    }

    public Evaluation() {
    }
    
    

    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public float getNoteEvenement() {
        return noteEvenement;
    }

    public void setNoteEvenement(float noteEvenement) {
        this.noteEvenement = noteEvenement;
    }
    
    

    @Override
    public String toString() {
        return "Evaluation{" + "evaluationId=" + evaluationId + ", idUser=" + idUser + ", idEvenement=" + idEvenement + ", noteEvenement=" + noteEvenement + '}';
    }
     
}

