
package Model;

//import-- Ctrl shift I.
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

        
        
        
 //Fields       
public abstract class Part {
    private final IntegerProperty partID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;
    

//Constructor
public Part(int partID, String name, double price, int inStock,int min, int max)
{
    this.partID = new SimpleIntegerProperty(partID);
    this.name = new SimpleStringProperty(name);
    this.price= new SimpleDoubleProperty(price);
    this.inStock= new SimpleIntegerProperty(inStock);
    this.min= new SimpleIntegerProperty(min);
    this.max= new SimpleIntegerProperty(max);
    
}

//Methods- Setters and Getters.

public void setPartID(int partID){
    this.partID.set(partID);
}
public int getPartID(){
    return partID.get();
}
public IntegerProperty partIDProperty(){
    return partID;
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






  
    
  
    
    
    
   
    
    
    
    
    
    

