package com.greenhouse.ClientSide;

import com.greenhouse.ServerSide.Plant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class AlterPlantWindow extends JFrame implements ActionListener {

    private JFrame plantWindow;
    private JLabel plantLabel;
    private JButton addPlantButton;
    private JButton harvestPlantButton;
    private JLabel plantTypeLabel;
    private JLabel plantHarvestDateLabel;
    private JLabel plantPrefTempLabel;
    private JTextField plantTypeField;
    private JTextField plantHarvestDateField;
    private JTextField plantPrefTempField;
    private JTextField idOfPlantField;
    private JPanel plantPanel;


    public AlterPlantWindow() {
        plantWindow = new JFrame();

        addPlantButton = new JButton("Add plant");
        harvestPlantButton = new JButton("Harvest Plant with ID:");

        plantLabel = new JLabel("Enter info on the plant");
        plantTypeLabel = new JLabel("Plant type:");
        plantHarvestDateLabel = new JLabel("Harvest date:");
        plantPrefTempLabel = new JLabel("Preferred temperature:");

        plantTypeField = new JTextField(10);
        plantHarvestDateField = new JTextField(10);
        plantPrefTempField = new JTextField(10);
        idOfPlantField = new JTextField(10);

        plantPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10,10,10,10);

        plantPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        constraints.gridx = 0;
        constraints.gridy = 0;
        plantPanel.add(plantLabel, constraints);

        constraints.gridx = 1;
        plantPanel.add(addPlantButton, constraints);
        addPlantButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 1;
        plantPanel.add(plantTypeLabel, constraints);

        constraints.gridx = 1;
        plantPanel.add(plantTypeField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        plantPanel.add(plantHarvestDateLabel, constraints);

        constraints.gridx = 1;
        plantPanel.add(plantHarvestDateField, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        plantPanel.add(plantPrefTempLabel, constraints);

        constraints.gridx = 1;
        plantPanel.add(plantPrefTempField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        plantPanel.add(harvestPlantButton, constraints);
        harvestPlantButton.addActionListener(this);

        constraints.gridx = 1;
        plantPanel.add(idOfPlantField, constraints);

        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        plantWindow.add(plantPanel, BorderLayout.CENTER);
        plantWindow.setTitle("Alter Plant Window");
        plantWindow.pack();
        plantWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addPlantButton) {
            try {
                String requestType = "addPlant";
                String plantType = plantTypeField.getText();
                LocalDate harvestDate = LocalDate.parse(plantHarvestDateField.getText());
                int prefTemp = Integer.parseInt(plantPrefTempField.getText());

                new SocketClient(requestType, plantType, harvestDate, prefTemp);
            }
            catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
        else if (e.getSource() == harvestPlantButton) {
            String requestType = "removePlant";
            int plantID = Integer.parseInt(idOfPlantField.getText());
            try {
                new SocketClient(requestType, plantID);
            }
            catch (IOException | ClassNotFoundException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
