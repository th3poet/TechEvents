/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Users;
import interfaces.IUsers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.userservices;

/**
 * FXML Controller class
 *
 * @author thepoet
 */
public class Dashboard_AdminController implements Initializable {

    @FXML
    private VBox pnl_scroll;
    @FXML
    private Label name;
    @FXML
    private ImageView profilephoto;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton list_user;
    @FXML
    private JFXButton list_event;
    @FXML
    private JFXButton list_organi;
    @FXML
    private JFXButton list_ticket;
    @FXML
    private JFXButton list_espace;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private AnchorPane pane;
    
    private AnchorPane home, add, listUsers, listOrgani , listEvents , listTickets, espace;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createPages();
    }    
    
    
    
    IUsers user = new userservices();
    void transferMessage(String email) {
        
        Users u1 = user.findByMail(email);
        String text = "Bienvenue "+u1.getFIRST_NAME();
        name.setText(text);
        String ImageUrl = u1.getPROFILE_PHOTO();
        File file = new File(ImageUrl);
        Image ima = new Image(file.toURI().toString());
        profilephoto.setImage(ima);
    }
    
    private void createPages() {
        try {
            home = FXMLLoader.load(getClass().getResource("/fxml/Overview.fxml"));
            listUsers = FXMLLoader.load(getClass().getResource("/fxml/ListUsers.fxml"));
            listEvents = FXMLLoader.load(getClass().getResource("/fxml/ListEvents.fxml"));
            listOrgani = FXMLLoader.load(getClass().getResource("/fxml/ListOrgani.fxml"));
            listTickets = FXMLLoader.load(getClass().getResource("/fxml/ListTickets.fxml"));
            espace      =   FXMLLoader.load(getClass().getResource("/fxml/EspacePartage.fxml"));
                    
            //set up default node on page load
            setNode(home);
        } catch (IOException ex) {
            Logger.getLogger(Dashboard_AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void setNode(Node node) {
        pnl_scroll.getChildren().clear();
        pnl_scroll.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void openListStudent(ActionEvent event) {
        setNode(listUsers);
    }
    
    @FXML
    private void openhome(ActionEvent event) {
        setNode(home);
    }

    @FXML
    private void logout(ActionEvent event) {
        
        pane.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Signin.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openlistevents(ActionEvent event) {
        setNode(listEvents);
    }

    @FXML
    private void openlistorgani(ActionEvent event) {
        setNode(listOrgani);
    }

    @FXML
    private void openlisttickets(ActionEvent event) {
        setNode(listTickets);
    }

    @FXML
    private void openespace(ActionEvent event) {
        setNode(espace);
    }

    

    
}
