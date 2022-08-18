/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tseringnorbu;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tsering
 */
public class MainClass extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //load fxml file and display it in the stage:
        
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/MainScreen.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
         InhousePart ihp1 = new InhousePart(1,"LCD",70.00,10,5,10,1);
        InhousePart ihp2 = new InhousePart(2,"Battery",50.00,10,5,10,1);
        InhousePart ihp3 = new InhousePart(3,"Speaker",30.00,10,5,10,1);
        
        OutsourcedPart osp = new OutsourcedPart(4,"CPU",80.00,15,5,15,"ABC");
        Inventory.allParts.add(ihp1);
        Inventory.allParts.add(ihp2);
        Inventory.allParts.add(ihp3);
        Inventory.allParts.add(osp);
        
        Product P1 = new Product(1,"Samsung",200.00,20,5,10);
        Product P2 = new Product(2,"Apple",300.00,20,5,10);
        Product P3 = new Product(3,"Blackberry",100.00,20,5,10);
        
        Inventory.products.add(P1);
        Inventory.products.add(P2);
        Inventory.products.add(P3);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
         // starts the FX toolkit, instantiates this class, 
        // and calls start(...) on the FX Application thread:
    }
    
}
