package com.greenhouse.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class AlterConditionWindow extends JFrame implements ActionListener {
    //JFrame and JButton
    private JFrame condWindow;
    private JButton changeTempButton;
    private JButton changeHumButton;
    private JButton startWaterButton;
    private JTextField condTempField;
    private JTextField condHumField;
    private JPanel condPanel;
    private JButton stopWaterButton;
    private JButton currentConditions;

    public AlterConditionWindow() {
        // create a new frame to store text field and button with name condWindow

        condWindow = new JFrame();

        // create a new buttons
        changeTempButton = new JButton("Change Temperature to");
        changeHumButton = new JButton("Change Humidity to");
        startWaterButton = new JButton("Start Watering");
        stopWaterButton = new JButton("Stop Watering");

        // create textfield and specify the place
        condTempField = new JTextField(10);
        condHumField = new JTextField(10);

        // Create a new buttons
        currentConditions = new JButton("Get Current Conditions");

        // Create a new panel
        condPanel = new JPanel(new GridBagLayout());

        // Creates a grid, fills from the left, and padding.
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(30,25,15,25);

        // Set the border from the frame and make a border object
        condPanel.setBorder(BorderFactory.createEmptyBorder(20, 75, 10, 75));

        // Place for components and adding listeners
        constraints.gridx = 0;
        constraints.gridy = 0;
        condPanel.add(currentConditions, constraints);
        currentConditions.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 1;
        condPanel.add(changeTempButton, constraints);
        changeTempButton.addActionListener(this);

        constraints.gridx = 1;
        condPanel.add(condTempField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        condPanel.add(changeHumButton, constraints);
        changeHumButton.addActionListener(this);

        constraints.gridx = 1;
        condPanel.add(condHumField, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        condPanel.add(startWaterButton, constraints);
        startWaterButton.addActionListener(this);

        constraints.gridy = 3;
        constraints.gridx = 1;
        condPanel.add(stopWaterButton, constraints);
        stopWaterButton.addActionListener(this);

        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        // Create and set up the window and setting close operation thereafter display the window
        condWindow.add(condPanel, BorderLayout.CENTER);
        condWindow.setTitle("Alter Condition Window");
        condWindow.pack();
        condWindow.setVisible(true);
    }


    @Override
    //  This gets called when button is clicked after that client send a request to the SocketClient
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == currentConditions){
            try {
                String requestType = "getConditions";
                new SocketClient(requestType);
            }
            catch (IOException | ClassNotFoundException conditionsError){
                conditionsError.printStackTrace();
            }
        }
        if(e.getSource() == changeTempButton) {
            try {
                String requestType = "changeTemperature";
                double temperature = Double.parseDouble(condTempField.getText());
                new SocketClient(requestType, temperature);
            }
            catch (IOException | ClassNotFoundException temperatureError){
                temperatureError.printStackTrace();
            }
        }
        else if (e.getSource() == changeHumButton) {
            try {
                String requestType = "changeHumidity";
                double humidity = Double.parseDouble(condHumField.getText());
                new SocketClient(requestType, humidity);
            }
            catch (IOException | ClassNotFoundException humidityError){
                humidityError.printStackTrace();
            }
        }
        else if (e.getSource() == startWaterButton) {
            try {
                String requestType = "startWatering";
                new SocketClient(requestType);
            }
            catch (IOException | ClassNotFoundException wateringStartError){
                wateringStartError.printStackTrace();
            }
        }

        else if (e.getSource() == stopWaterButton){
            try {
                String requestType = "stopWatering";
                new SocketClient(requestType);
            }
            catch (IOException | ClassNotFoundException wateringStopError){
                wateringStopError.printStackTrace();
            }
        }
    }
}
