package com.greenhouse.ClientSide;

import com.greenhouse.ServerSide.Plant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

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
        LocalDate localDate = LocalDate.now().plusDays(1);

        plantWindow = new JFrame();

        addPlantButton = new JButton("Add plant");
        harvestPlantButton = new JButton("Harvest Plant with ID:");

        plantLabel = new JLabel("Enter info on the plant");
        plantTypeLabel = new JLabel("Plant type:");
        plantHarvestDateLabel = new JLabel("Harvest date:");
        plantPrefTempLabel = new JLabel("Preferred temperature:");

        plantTypeField = new JTextField(12);
        plantHarvestDateField = new JTextField(12);
        plantPrefTempField = new JTextField(12);
        idOfPlantField = new JTextField(12);

        plantPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(30,25,15,25);

        plantPanel.setBorder(BorderFactory.createEmptyBorder(20, 75, 10, 75));
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

        plantWindow.add(plantPanel, BorderLayout.CENTER);
        plantWindow.setSize(800,600);
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
            } catch (Exception t) {
            //catch (IOException | ClassNotFoundException ioException) {
                System.out.println("Something went wrong when trying to add your plant. " +
                        "Please check all inputs.");
                //ioException.printStackTrace();
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
