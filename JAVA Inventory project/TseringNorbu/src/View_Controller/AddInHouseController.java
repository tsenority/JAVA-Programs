/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class AddInHouseController implements Initializable {

    @FXML
    private Label AddPart;

    @FXML
    private RadioButton InHouse;
    
    @FXML
    private ToggleGroup Radio1;

    @FXML
    private RadioButton Outsourced;

    @FXML
    private TextField IDTextfield;

    @FXML
    private TextField MachineIDTextfield;

    @FXML
    private TextField MaxTextfield;

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
    private Label MachineID;

    @FXML
    private Label Min;

    @FXML
    private TextField MinTextfield;

    

    @FXML
    private Button Cancel;
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
    void OutsourcedSelect(ActionEvent event) throws IOException {
         Parent OutsourcedParent =  FXMLLoader.load(getClass().getResource("AddOutsourced.fxml"));
       Scene  OutsourcedScene = new Scene(OutsourcedParent);
       //This line gets the stage information
       Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(OutsourcedScene);
       window.show();
 
    }
      @FXML
    void inHouseSelect(ActionEvent event) throws IOException {
       
        
       
    }
    
    @FXML
    private Button Save;

    @FXML
    public void SaveHandler(ActionEvent event) throws IOException {
        //use the data from the textfields to construct a new Inhouse Part, and add it to the allParts arraylist in Inventory.
        // public InhousePart(int partID, String name, double price, int inStock, int min, int max,int machineID){
         if(isValid()){
         int PartID = Integer.parseInt(IDTextfield.getText());
         int PartIN = Integer.parseInt(InvTextfield.getText());
         int Part1 = Integer.parseInt(MachineIDTextfield.getText());
         int Part2 = Integer.parseInt(MaxTextfield.getText());
         int Part3 = Integer.parseInt(MinTextfield.getText());
        double Part4 = Double.parseDouble(PriceTextfield.getText());
        String Part5 = NameTextfield.getText();
        
        InhousePart T = new InhousePart(PartID, Part5, Part4, PartIN, Part3, Part2, Part1);
        
        Inventory.allParts.add(T);
        
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
        InHouse.setSelected(true);
    }
        public boolean isValid(){
        boolean valid = true;
        int PartIN = Integer.parseInt(InvTextfield.getText());
        int Part2 = Integer.parseInt(MaxTextfield.getText());
         int Part3 = Integer.parseInt(MinTextfield.getText());
        
       if(Part2<Part3){
            msg+= "Maxmimum value must be greater than minimum \n";
            valid = false;
        }
         if(Part3>Part2){
            msg+= "Minimum value must be less than maximum \n";
            valid = false;
        }
        if(PartIN<Part3|| PartIN>Part2){
            msg+= "Inventory must be between min and max \n";
            valid = false;
        }
        
            
        
        
        return valid;
    }

    }



      
   


     




    
    

