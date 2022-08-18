
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Product {

    public Product selectedProduct;
   public static int numParts;
  public ObservableList<Part> associatedParts= FXCollections.observableArrayList();
    public  ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    public void setAssociatedParts(ObservableList<Part> Productpart){
       associatedParts = Productpart; 
    }
    
    private final IntegerProperty productID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;
    
public Product()
{
    this.productID = new SimpleIntegerProperty(numParts++);
    this.name = new SimpleStringProperty("");
    this.price= new SimpleDoubleProperty(0.0);
    this.inStock= new SimpleIntegerProperty(0);
    this.min= new SimpleIntegerProperty(0);
    this.max= new SimpleIntegerProperty(0);
    
}

    public Product(int productID, String name, double price, int inStock,int min, int max) {
       this.productID = new SimpleIntegerProperty(numParts++);
    this.name = new SimpleStringProperty(name);
    this.price= new SimpleDoubleProperty(price);
    this.inStock= new SimpleIntegerProperty(inStock);
    this.min= new SimpleIntegerProperty(min);
    this.max= new SimpleIntegerProperty(max);//hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public void setProductID(int productID){
    this.productID.set(productID);
}
public int getProductID(){
    return productID.get();
}
public IntegerProperty productIDProperty(){
    return productID;
}

public void setName(String name){
    this.name.set(name);
}
public String getName(){
    return name.get();
}
public StringProperty nameProperty(){
    return name;
}

public void setPrice(double price){
    this.price.set(price);
}
public double getPrice(){
    return price.get();
}
public DoubleProperty priceProperty(){
    return price;
}

public void setinStock(int inStock){
    this.inStock.set(inStock);
}
public int getinStock(){
    return inStock.get();
}
public IntegerProperty inStockProperty(){
    return inStock;
}

public void setMin(int min){
    this.min.set(min);
}
public int getMin(){
    return min.get();
}
public IntegerProperty minProperty(){
    return min;
}

public void setMax(int max){
    this.max.set(max);
}
public int getMax(){
    return max.get();
}
public IntegerProperty MaxProperty(){
    return max;
}


    
  
    
    
}

