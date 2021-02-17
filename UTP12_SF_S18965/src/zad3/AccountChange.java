package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccountChange implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent){
        Double newVal = (Double) propertyChangeEvent.getNewValue();
        double val = newVal.doubleValue();
        if(val>=0){
            System.out.println(propertyChangeEvent.getPropertyName()+ ": Value changed from "+ propertyChangeEvent.getOldValue()+ " to " +propertyChangeEvent.getNewValue());
        }
        else{
            System.out.println(propertyChangeEvent.getPropertyName()+ ": Value changed from "+ propertyChangeEvent.getOldValue()+ " to " +propertyChangeEvent.getNewValue()+", balance < 0!");
        }
    }
}
