/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.Part;
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
public class ModifyPartInController implements Initializable {
    @FXML
    private Label ModifyPart;

    @FXML
    private RadioButton InHouse;
    
    @FXML
    private ToggleGroup Radio3;

    @FXML
    private RadioButton Outsourced;

    @FXML
    private TextField IDTextfield;

    @FXML
    private TextField MachineTextfield;

    @FXML
    private TextField MaxTextField;

    @FXML
    private TextField PriceTextField;

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
    void InHouseSelect(ActionEvent event) {

    }

    @FXML
    void OutsourcedSelect(ActionEvent event) throws IOException {
        Parent ModifyPartsParent =  FXMLLoader.load(getClass().getResource("ModifyPartOutsrc.fxml"));
       Scene  ModifyPartScene = new Scene(ModifyPartsParent);
       //This line gets the stage information
       Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(ModifyPartScene);
       window.show();

    }

    @FXML
    void SaveHandler(ActionEvent event) throws IOException {
         if(isValid()){
          
         
       // Inventory.selectedPart.setName(Part5);
        int PartID = Integer.parseInt(IDTextfield.getText());
        int PartIN = Integer.parseInt(InvTextfield.getText());
        int Part1 = Integer.parseInt(MachineTextfield.getText());
         int Part2 = Integer.parseInt(MaxTextField.getText());
         int Part3 = Integer.parseInt(MinTextfield.getText());
         double Part4 = Double.parseDouble(PriceTextField.getText());
         String Part5 = NameTextfield.getText();
        
         Inventory.selectedPart.setinStock(PartIN);
          if(!(Inventory.selectedPart instanceof InhousePart)){
             
              for(int i = 0; i<Inventory.allParts.size(); i++)
              {
                  if(Inventory.allParts.get(i).getPartID()== Inventory.selectedPart.getPartID()){
                      Inventory.allParts.remove(Inventory.allParts.get(i));
                  }
                  
              }   
              Inventory.selectedPart = new InhousePart(PartID,Part5,Part4,PartIN,Part3,Part2,Part1);
              Inventory.allParts.add(Inventory.selectedPart);
              
         }
          
         
         ((InhousePart)Inventory.selectedPart).setmachineID(Part1);
        
         Inventory.selectedPart.setMax(Part2);
         
         Inventory.selectedPart.setMin(Part3);
        
        Inventory.selectedPart.setPrice(Part4);
        
        Inventory.selectedPart.setName(Part5);
        
        
        
        
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
        if(Inventory.selectedPart instanceof InhousePart){
            
        
        MachineTextfield.setText(((InhousePart)(Inventory.selectedPart)).getmachineID()+"");
        }
        IDTextfield.setText(Inventory.selectedPart.getPartID()+"");
        InvTextfield.setText(Inventory.selectedPart.getinStock()+"");
        MinTextfield.setText(Inventory.selectedPart.getMin()+"");
        MaxTextField.setText(Inventory.selectedPart.getMax()+"");
        NameTextfield.setText(Inventory.selectedPart.getName()+"");
        PriceTextField.setText(Inventory.selectedPart.getPrice()+"");
        
        
        
        
    }
     public boolean isValid(){
        boolean valid = true;
        int PartIN = Integer.parseInt(InvTextfield.getText());
        int Part2 = Integer.parseInt(MaxTextField.getText());
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