package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class AccountLimitator implements VetoableChangeListener {

    public double min;
    public AccountLimitator(double i) {
        this.min=i;
    }

    @Override
    public void vetoableChange(PropertyChangeEvent propertyChangeEvent) throws PropertyVetoException {
        Double newVal = (Double) propertyChangeEvent.getNewValue();
        double val = newVal.doubleValue();
        if (val< min)
            throw new PropertyVetoException(propertyChangeEvent.getPropertyName() + ": Unacceptable value change: "+ propertyChangeEvent.getNewValue(),propertyChangeEvent);
        }
    }

