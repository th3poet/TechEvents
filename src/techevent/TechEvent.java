/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent;


import dataSource.connexion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author toshiba.pc
 */
public class TechEvent extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/liaison.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();}
        
     catch (IOException ex) {
            Logger.getLogger(TechEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @param args the command line arguments
     */
   
    
    public static void main(String[] args) {
        launch(args);
        connexion.getInstance();
        
        
        //Tickets t =new Tickets(1,1,"match",new java.sql.Date(2018-1900,2,2),50,"50");
      //  sp.ajouterticket(t);
       // System.out.println(t.toString());
       //ServiceReservation sr =new ServiceReservation();
    //G_Reservation rs =new G_Reservation(1,1,50,new java.sql.Date(2018-1900,2,2));
   // sr.ajouterreservation(rs);
   
   
    }
    
}
