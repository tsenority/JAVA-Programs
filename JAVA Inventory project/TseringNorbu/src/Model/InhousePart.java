
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class InhousePart extends Part {
    private final IntegerProperty machineID;
    public InhousePart(int partID, String name, double price, int inStock, int min, int max,int machineID){
        
        super(partID, name, price, inStock, min, max);
       
        this.machineID=new SimpleIntegerProperty();
        setmachineID(machineID);
    }
    
 public void setmachineID(int machineID){
    this.machineID.set(machineID);
}
public int getmachineID(){
    return machineID.get();
}
public IntegerProperty machineIDProperty(){
    return machineID;
}
    
    
    
    
   
  
    
   

 
    
}
