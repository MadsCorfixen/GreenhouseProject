package com.greenhouse.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class AlterPlantWindow extends JFrame implements ActionListener {
    //JFrame and JButton
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
        LocalDate localDate = LocalDate.now().plusDays(1);

        // Create a new frame to store text field and button with name plantWindow
        plantWindow = new JFrame();

        // Create new buttons
        addPlantButton = new JButton("Add plant");
        harvestPlantButton = new JButton("Harvest Plant with ID:");

        // Create labels to display text
        plantLabel = new JLabel("Enter info on the plant");
        plantTypeLabel = new JLabel("Plant type:");
        plantHarvestDateLabel = new JLabel("Harvest date:");
        plantPrefTempLabel = new JLabel("Preferred temperature:");

        // Create fields for the user to write in, with width 12
        plantTypeField = new JTextField(12);
        plantHarvestDateField = new JTextField(12);
        plantPrefTempField = new JTextField(12);
        idOfPlantField = new JTextField(12);

        // Create a panel to add buttons and a specific layout
        plantPanel = new JPanel(new GridBagLayout());

        // Creates a grid, fills from the left, and padding.
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(30,25,15,25);

        // Set the border from the frame and make a border object
        plantPanel.setBorder(BorderFactory.createEmptyBorder(20, 75, 10, 75));

        // Place for components
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
        plantHarvestDateField.setText(localDate.toString());
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

        // Create and set up the window and setting close operation thereafter display the window
        plantWindow.add(plantPanel, BorderLayout.CENTER);
        plantWindow.setSize(800,600);
        plantWindow.setTitle("Alter Plant Window");
        plantWindow.pack();
        plantWindow.setVisible(true);
    }

    @Override
    // This gets called when button is clicked after that client send a request to the SocketClient
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addPlantButton) {
            try {
                String requestType = "addPlant";
                String plantType = plantTypeField.getText();
                LocalDate harvestDate = LocalDate.parse(plantHarvestDateField.getText());
                int prefTemp = Integer.parseInt(plantPrefTempField.getText());

                new SocketClient(requestType, plantType, harvestDate, prefTemp);
            } catch (Exception t) {
                System.out.println("Something went wrong when trying to add your plant. " +
                        "Please check all inputs.");
            }
        }
        else if (e.getSource() == harvestPlantButton) {
            String requestType = "removePlant";
            int plantID = Integer.parseInt(idOfPlantField.getText());
            try {
                new SocketClient(requestType, plantID);
            } catch (Exception ex) {
                System.out.println("Could not harvest the requested plant. Please check " +
                        "if plant number is correct.");
            }
        }
    }
}
