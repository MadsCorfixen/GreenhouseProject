package com.greenhouse.ClientSide;

import com.greenhouse.ServerSide.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private JButton ripeButton;

    public ClientGUI() {
        LocalDate localDate = LocalDate.now();

        mainWindow = new JFrame();
        welcomeLabel = new JLabel("Welcome to the Greenhouse!");
        welcomeInfo = new JLabel("Today's date " + localDate);

        alterPlantButton = new JButton("Alter Plants");
        alterConditionsButton = new JButton("Alter Conditions");
        seePlantsButton = new JButton("See Plants");
        getLogButton = new JButton("Get Log");
        exitAndSaveButton = new JButton("Save and Exit!");
        ripeButton = new JButton("Check Ripeness");
        panel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(30,25,25,25);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 75, 25, 75));
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
        panel.add(getLogButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(welcomeInfo, constraints);

        constraints.gridy = 1;
        panel.add(seePlantsButton, constraints);

        constraints.gridy = 2;
        panel.add(ripeButton, constraints);

        constraints.gridy = 3;
        panel.add(exitAndSaveButton, constraints);


        alterConditionsButton.addActionListener(this);
        alterPlantButton.addActionListener(this);
        seePlantsButton.addActionListener(this);
        getLogButton.addActionListener(this);
        exitAndSaveButton.addActionListener(this);
        ripeButton.addActionListener(this);

        mainWindow.add(panel, BorderLayout.CENTER);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setTitle("Greenhouse 302");
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
            try {
                String requestType = "getLog";
                new SocketClient(requestType);
            }
            catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
        else if(e.getSource() == ripeButton){
            try {
                String requestType = "checkRipeness";
                new SocketClient(requestType);
            }
            catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
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
