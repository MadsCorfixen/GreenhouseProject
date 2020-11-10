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
    private JLabel welcomeLabel;
    private JLabel welcomeInfo;

    public ClientGUI() {
        mainWindow = new JFrame();
        welcomeLabel = new JLabel("Welcome to the Greenhouse!");
        welcomeInfo = new JLabel("Today is so so :-)");

        alterPlantButton = new JButton("Alter Plants");
        alterConditionsButton = new JButton("Alter Conditions");
        seePlantsButton = new JButton("See Plants");
        getLogButton = new JButton("Get Log");
        exitAndSaveButton = new JButton("Exit and save!");
        panel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10,10,10,10);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(welcomeLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(alterPlantButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(alterConditionsButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(exitAndSaveButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(welcomeInfo, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(seePlantsButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(getLogButton, constraints);


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
