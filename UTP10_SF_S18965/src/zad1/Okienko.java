package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.*;

public class Okienko extends JPanel {

    JButton newThread;
    JButton stopThread;
    JPanel titlePanel;
    JPanel textareaPanel;
    JTextArea jTextArea =new JTextArea();
    ExecutorService executor =Executors.newCachedThreadPool();
    NewThread<Integer> callable;
    FutureTask<Integer> task;
    boolean threadStop=false;

    public Okienko() {
        newThread = new JButton("Create New Thread");
        stopThread = new JButton("Stop Thread");
        titlePanel = new JPanel(new GridLayout(2,1));
        titlePanel.add(newThread);
        titlePanel.add(stopThread);
        textareaPanel = new JPanel(new GridLayout());
        JScrollPane scroll = new JScrollPane (jTextArea);
        add(scroll, BorderLayout.NORTH);
        textareaPanel.add(scroll);

        JPanel southBtnPanel = new JPanel(new GridLayout(1, 10));

        final int[] counter = {1};

        newThread.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent n)
            {
                JButton jButton = new JButton("T"+ counter[0]++);
                southBtnPanel.add(jButton);
                southBtnPanel.revalidate();
                String index=jButton.getText().substring(1);

                jButton.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent k) {
                    }

                    @Override
                    public void keyPressed(KeyEvent k) {
                        jButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                CancelThread cancelThread= new CancelThread(index,jTextArea,jButton,southBtnPanel);
                                FutureTask task = new FutureTask(cancelThread);
                                executor.submit(task);
                            }
                        });
                        }

                    @Override
                    public void keyReleased(KeyEvent k) {
                    }
                });

                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        callable=new NewThread<>(index,jTextArea,jButton,southBtnPanel,Okienko.this);
                        task = new FutureTask<>(callable);
                        if(jButton.getText().startsWith("T") && jButton.getText().endsWith(index)){
                            executor.submit(task);
                            jButton.setText("Suspend T"+index);
                        }else if(jButton.getText().startsWith("Suspend")){
                            try {
                                threadStop=true;
                                synchronized (task){
                                    callable.wait();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            jButton.setText("Continue T" + index);
                            jTextArea.append("Thread "+index+": Suspended!\n");
                        }else{
                            if(jButton.getText().equals("Continue T"+index)){
                                threadStop=false;
                                synchronized (task){
                                   task.notify();
                                }
                                jButton.setText("Suspend T"+index);
                                jTextArea.append("Thread "+index+": Continue!\n");
                            }
                        }
                    }
                });
                stopThread.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!jButton.getText().equals("T"+index)) {
                            StopThread stopThread = new StopThread(index, jTextArea, jButton, southBtnPanel);
                            FutureTask ftask = new FutureTask(stopThread);
                            executor.execute(ftask);
                                /*task.cancel(true);
                            try {
                                    task.wait();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }*/
                        }
                    }
                });
            }
        });

        setLayout(new BorderLayout());
        add(textareaPanel,BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
        add(southBtnPanel, BorderLayout.SOUTH);
    }

    public static void createAndShowGui() {
        Okienko mainPanel = new Okienko();
        JFrame frame = new JFrame("Threadpool");
        frame.setPreferredSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
