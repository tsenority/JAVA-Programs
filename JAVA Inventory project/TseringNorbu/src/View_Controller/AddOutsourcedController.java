/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.OutsourcedPart;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tsering
 */
public class AddOutsourcedController implements Initializable {
    @FXML
    private Label AddPart;

    @FXML
    private RadioButton InHouse;
    
    
    @FXML
    private ToggleGroup Radio2;

    @FXML
    private RadioButton Outsourced;

    @FXML
    private TextField IDTextfield;

    @FXML
    private TextField CompanyTextfield;

    @FXML
    private TextField MaxTextField;

    @FXML
    private TextField PriceTextfield;

    @FXML
    private TextField InvTextfield;

    @FXML
    private TextField NameTextfield;

    @FXML
    private Label ID;

    @FXML
    private Label Max;

    @FXML
    private Label Price;

    @FXML
    private Label Inv;

    @FXML
    private Label Name;

    @FXML
    private Label CompanyName;

    @FXML
    private Label Min;

    @FXML
    private TextField MinTextfield;

    @FXML
    private Button SaveButton;
    
    @FXML
    private Button CancelButton;
    String msg = "";

    @FXML
    void CancelHandler(ActionEvent event) throws IOException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Cancelling");
        alert.setContentText("Do you want to cancel and go back to main screen?");        
        alert.showAndWait();
        
        Parent MainScreenParent =  FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
       Scene MainScreenScene = new Scene(MainScreenParent);
       //This line gets the stage information
       Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(MainScreenScene);
       window.show();

    }

   @FXML
    void InHouseSelect(ActionEvent event) throws IOException {
        Parent InHouseParent =  FXMLLoader.load(getClass().getResource("AddInHouse.fxml"));
       Scene  InHouseScene = new Scene(InHouseParent);
       //This line gets the stage information
       Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(InHouseScene);
       window.show();

    }

    @FXML
    void OutsourcedSelect(ActionEvent event) {

    }

    @FXML
    void SaveHandler(ActionEvent event) throws IOException {
        //public OutsourcedPart(int partID, String name, double price, int inStock, int min, int max, String companyName)
        if(isValid()){
            int Part1 = Integer.parseInt(IDTextfield.getText());
        
        String Part2 = NameTextfield.getText();
         double Part3 = Double.parseDouble(PriceTextfield.getText());
         int Part4 = Integer.parseInt(InvTextfield.getText());
         int Part5 = Integer.parseInt(MinTextfield.getText());
         int Part6 = Integer.parseInt(MaxTextField.getText());
         String Part7 =  CompanyTextfield.getText();
         
         OutsourcedPart outsrc = new OutsourcedPart(Part1, Part2, Part3, Part4, Part5, Part6, Part7);
         
         Inventory.allParts.add(outsrc);
         
          Parent MainScreenParent =  FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
       Scene MainScreenScene = new Scene(MainScreenParent);
       //This line gets the stage information
       Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(MainScreenScene);
       window.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setHeaderText("Invalid input");
        alert.setContentText(msg);

        alert.showAndWait();
          msg = "";  
        }
         
    
         
        
       
         
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Outsourced.setSelected(true);
    }
    
         public boolean isValid(){
        boolean valid = true;
        int Part4 = Integer.parseInt(InvTextfield.getText());
        int Part6 = Integer.parseInt(MaxTextField.getText());
         int Part5 = Integer.parseInt(MinTextfield.getText());
        
       if(Part6<Part5){
            msg+= "Maxmimum value must be greater than minimum \n";
            valid = false;
        }
         if(Part5>Part6){
            msg+= "Minimum value must be less than maximum \n";
            valid = false;
        }
        if(Part4<Part5|| Part4>Part6){
            msg+= "Inventory must be between min and max \n";
            valid = false;
        }
        
            
        
        
        return valid;
    }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



  
    

