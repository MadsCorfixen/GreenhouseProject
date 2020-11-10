package com.greenhouse.ClientSide;

import com.greenhouse.ServerSide.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class ClientGUI extends JFrame implements ActionListener {

    private JFrame mainWindow;
    private JButton alterPlantButton;
    private JButton alterConditionsButton;
    private JButton seePlantsButton;
    private JButton getLogButton;
    private JPanel panel;
    private JButton exitAndSaveButton;

    public ClientGUI() {
        mainWindow = new JFrame();

        alterPlantButton = new JButton("Alter Plants");
        alterConditionsButton = new JButton("Alter Conditions");
        seePlantsButton = new JButton("See Plants");
        getLogButton = new JButton("Get Log");
        exitAndSaveButton = new JButton("Exit and save!");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(alterPlantButton);
        panel.add(alterConditionsButton);
        panel.add(seePlantsButton);
        panel.add(getLogButton);
        panel.add(exitAndSaveButton);

        alterConditionsButton.addActionListener(this);
        alterPlantButton.addActionListener(this);
        seePlantsButton.addActionListener(this);
        getLogButton.addActionListener(this);
        exitAndSaveButton.addActionListener(this);

        mainWindow.add(panel, BorderLayout.CENTER);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setTitle("Our GUI");
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    public static void main(String[] args) {
        new ClientGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == alterPlantButton){
            new AlterPlantWindow();
        }
        else if(e.getSource() == alterConditionsButton){
            new AlterConditionWindow();
        }
        else if(e.getSource() == seePlantsButton){
            try {
                String requestType = "getPlants";
                new SocketClient(requestType);
            }
            catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
        else if(e.getSource() == getLogButton){
            System.out.println("Nope, ingen løg til dig!");
        }
        else if(e.getSource() == exitAndSaveButton){
            try {
                String requestType = "exitAndSave";
                new SocketClient(requestType);
                System.exit(0);
            }
            catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
