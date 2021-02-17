package zad1;

import javax.swing.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CancelThread implements java.util.concurrent.Callable<Integer> {

    String index;
    JTextArea jTextArea;
    JButton jButton;
    JPanel jPanel;

    public CancelThread(String index, JTextArea jTextArea, JButton jButton, JPanel jPanel) {
        this.index = index;
        this.jTextArea = jTextArea;
        this.jButton = jButton;
        this.jPanel = jPanel;
    }

    @Override
    public Integer call() throws Exception {
        jTextArea.append("Thread " + index +": Cancelled!\n");
        jButton.setText("T"+index+" Cancelled");
        jButton.setEnabled(false);
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        jPanel.remove(jButton);
        jPanel.revalidate();
        return 0;
    }
}
