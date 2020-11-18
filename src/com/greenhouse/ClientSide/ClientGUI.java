package com.greenhouse.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDate;

public class ClientGUI extends JFrame implements ActionListener {
    // JFrame and JButton
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
    boolean alreadyExecuted;

    public ClientGUI() {
        LocalDate localDate = LocalDate.now();

        // Create a new frame to store text field and button with name mainWindow
        mainWindow = new JFrame();

        // Create a label to display text
        welcomeLabel = new JLabel("Welcome to the Greenhouse!");
        welcomeInfo = new JLabel("Today's date " + localDate);

        // Create new buttons
        alterPlantButton = new JButton("Alter Plants");
        alterConditionsButton = new JButton("Alter Conditions");
        seePlantsButton = new JButton("See Plants");
        getLogButton = new JButton("Get Log");
        exitAndSaveButton = new JButton("Save and Exit!");
        ripeButton = new JButton("Check Ripeness");

        // Create a panel to add buttons and  a specific layout
        panel = new JPanel(new GridBagLayout());

        // Creates a grid, fills from the left, and padding.
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(30,25,15,25);

        // Set the border from the frame and make a border object
        panel.setBorder(BorderFactory.createEmptyBorder(20, 75, 10, 75));

        // Place for components
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

        // Adding listeners to buttons
        alterConditionsButton.addActionListener(this);
        alterPlantButton.addActionListener(this);
        seePlantsButton.addActionListener(this);
        getLogButton.addActionListener(this);
        exitAndSaveButton.addActionListener(this);
        ripeButton.addActionListener(this);

        // Create and set up the window and setting close operation thereafter display the window
        mainWindow.add(panel, BorderLayout.CENTER);
        mainWindow.setTitle("Greenhouse 302");
        mainWindow.pack();
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Checking if the server is opened
        try {
            String requestType = "clientOpen";
            new SocketClient(requestType);
            System.out.println("Welcome to the Greenhouse :-)");
        }
        catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
            System.out.println("Hold on with doing stuff... The Server is closed. Please run the file 'Server.java' first");
            System.exit(0);
        }
    }
    // Main method that runs the ClientGUI constructor.
    public static void main(String[] args) {
        new ClientGUI();
    }

    @Override
    // This gets called when buttons are clicked after that client send a request to the  socketClient
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
