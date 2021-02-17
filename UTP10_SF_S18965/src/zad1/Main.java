package zad1;

import javax.swing.*;

import static zad1.Okienko.createAndShowGui;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

}
