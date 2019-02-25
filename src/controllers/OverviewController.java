/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IUsers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import services.userservices;

/**
 * FXML Controller class
 *
 * @author thepoet
 */
public class OverviewController implements Initializable {

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
    @FXML
    private Label lblnbuser;
    @FXML
    private Label lblorg;
    @FXML
    private Label lblnbevent;
    @FXML
    private Label lblnbarticle;
    @FXML
    private BarChart<?, ?> barchart;
    @FXML
    private NumberAxis NumberAxis;
    @FXML
    private CategoryAxis CategoryAxis;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userservices u1= new userservices();
        int nbusers = u1.nbusers();
        lblnbuser.setText(Integer.toString(nbusers));
        int nborg = u1.nborg();
        lblorg.setText(Integer.toString(nborg));
        int nbevent = u1.nbevent();
        lblnbevent.setText(Integer.toString(nbevent));
        int nbarticle = u1.nbarticle();
        lblnbarticle.setText(Integer.toString(nbarticle));
        
        XYChart.Series set1 = new XYChart.Series<>();
        
        set1.getData().add(new XYChart.Data("Nombre Utilisateurs",nbusers));
        set1.getData().add(new XYChart.Data("Nombre Organisteurs",nborg));
        set1.getData().add(new XYChart.Data("Nombre Evénements",nbevent));
        set1.getData().add(new XYChart.Data("Nombre Articles",nbarticle));
        
        barchart.getData().addAll(set1);
    }    
    
    
}
