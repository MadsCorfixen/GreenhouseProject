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


    public AlterConditionWindow() {
        condWindow = new JFrame();

        changeTempButton = new JButton("Change Temperature to");
        changeHumButton = new JButton("Change Humidity to");
        startWaterButton = new JButton("Start Watering for one hour");

        condTempField = new JTextField(10);
        condHumField = new JTextField(10);

        condPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10,10,10,10);

        condPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        constraints.gridx = 0;
        constraints.gridy = 0;
        condPanel.add(changeTempButton, constraints);
        changeTempButton.addActionListener(this);

        constraints.gridx = 1;
        condPanel.add(condTempField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        condPanel.add(changeHumButton, constraints);
        changeHumButton.addActionListener(this);

        constraints.gridx = 1;
        condPanel.add(condHumField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        condPanel.add(startWaterButton, constraints);
        startWaterButton.addActionListener(this);

        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        condWindow.add(condPanel, BorderLayout.CENTER);
        condWindow.setTitle("Alter Condition Window");
        condWindow.pack();
        condWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changeTempButton) {
            System.out.println("ChangeTemp - Tak!");

        }
        else if (e.getSource() == changeHumButton) {
            System.out.println("ChangeHum - Tak!");
//            boolean removecond = true;
//            int condID = Integer.parseInt(idOfcondField.getText());
//            new Client(condID, removecond);
        }
        else if (e.getSource() == startWaterButton) {
            System.out.println("Start sprinkler - Tak!");
//            boolean removecond = true;
//            int condID = Integer.parseInt(idOfcondField.getText());
//            new Client(condID, removecond);
        }
    }
}
