/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author thepoet
 */
public class homeController implements Initializable {

    @FXML
    private HBox groupHolder;
    @FXML
    private Group groupRegistered;
    @FXML
    private Group groupTarget;
    @FXML
    private Group groupGents;
    @FXML
    private Group groupLadies;
    @FXML
    private StackPane deptStackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
