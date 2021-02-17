package zad1;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class NewThread<T> implements java.util.concurrent.Callable<Integer> {

    String index;
    JTextArea jTextArea;
    JButton jButton;
    JPanel jPanel;
    boolean threadStop;
    Okienko okienko;

    public NewThread(String index, JTextArea jTextArea, JButton jButton, JPanel jPanel, Okienko okienko ) {
        this.index = index;
        this.jTextArea=jTextArea;
        this.jButton=jButton;
        this.jPanel=jPanel;
        //this.threadStop=threadStop;
        this.okienko=okienko;
    }

    @Override
    public Integer call() throws InterruptedException {

            int suma=0;
            int limit=1000*Integer.parseInt(index);

            while(suma<=limit){
                while(okienko.threadStop){
                    TimeUnit.MILLISECONDS.sleep(1);
                }

                int randomNumber = (int)(Math.random()*100)+1;
                suma+=randomNumber;
                jTextArea.append("Thread " + index + "(limit=" + limit+"): "+randomNumber+", suma= "+suma+"\n");
                jTextArea.revalidate();
                try {
                    TimeUnit.MILLISECONDS.sleep((int)(Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        jTextArea.append("Thread " + index +": Done!\n");
            jButton.setText("T"+index+" Done!");
            jButton.setEnabled(false);
        TimeUnit.MILLISECONDS.sleep(3000);
            jPanel.remove(jButton);
            jPanel.revalidate();
            return suma;

        }
}