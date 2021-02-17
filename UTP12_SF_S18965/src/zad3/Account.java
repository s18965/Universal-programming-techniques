package zad3;

import java.beans.*;
import java.beans.PropertyVetoException;
import java.io.Serializable;

public class Account implements Serializable {

    int count=1;
    double balance;
    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

    public Account(double i) {
        this.count=count++;
        this.balance=i;
    }

    public Account() {
        this.count++;
        this.balance=0;
    }

    void deposit(double i) throws PropertyVetoException {
       setBalance(balance+i);
    }

    public void withdraw(double i) throws PropertyVetoException {
        setBalance(balance-i);
    }

    void transfer(Account acc1, double i) throws PropertyVetoException {
        this.withdraw(i);
        acc1.deposit(i);
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        String s="Acc " + count + ": "+getBalance();
        return s;
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChange.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChange.removePropertyChangeListener(listener);
    }

    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        vetos.removeVetoableChangeListener(l);
    }

    public synchronized void setBalance(double aCount)
            throws PropertyVetoException {
        double oldValue = this.balance;
        vetos.fireVetoableChange((String.valueOf(count)), new Double(oldValue), new Double(aCount));
        this.balance = aCount;
        propertyChange.firePropertyChange((String.valueOf(count)), new Double(oldValue), new Double(aCount));
    }
}
