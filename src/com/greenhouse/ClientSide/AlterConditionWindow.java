package com.greenhouse.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class AlterConditionWindow extends JFrame implements ActionListener {

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
        condWindow = new JFrame();

        changeTempButton = new JButton("Change Temperature to");
        changeHumButton = new JButton("Change Humidity to");
        startWaterButton = new JButton("Start Watering");
        stopWaterButton = new JButton("Stop Watering");

        condTempField = new JTextField(10);
        condHumField = new JTextField(10);

        currentConditions = new JButton("Get Current Conditions");

        condPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10,10,10,10);

        condPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

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

        condWindow.add(condPanel, BorderLayout.CENTER);
        condWindow.setTitle("Alter Condition Window");
        condWindow.pack();
        condWindow.setVisible(true);
    }


    @Override
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
