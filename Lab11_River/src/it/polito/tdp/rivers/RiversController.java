package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.RiversModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtFMed;

    @FXML
    private Button btnSimula;

    @FXML
    private TextField txtK;
    
    private RiversModel model;
    
    public void setModel(RiversModel model){
    	this.model = model;
    	//Popolo la ComboBox
    	boxRiver.getItems().addAll(model.getRivers());
    }
    
    @FXML
    void doGetData(ActionEvent event) {
    	River r = boxRiver.getValue();
    	//System.out.println("Selezionato fiume: "+r.getName());
    	txtStartDate.setText(model.getFirstMeasure(r).toString());
    	txtEndDate.setText(model.getLastMeasure(r).toString());
    	txtNumMeasurements.setText(String.valueOf(r.getFlows().size()));
    	txtFMed.setText(String.format("%.5f", model.getMediumFlow(r)));
    }

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";

    }
}
