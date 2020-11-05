package com.greenhouse.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ActionListener {

    private JFrame mainWindow;
    private JButton alterPlantButton;
    private JButton alterConditionsButton;
    private JPanel panel;

    public ClientGUI() {
         mainWindow = new JFrame();

        alterPlantButton = new JButton("Alter Plants");
        alterConditionsButton = new JButton("Alter Conditions");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(alterPlantButton);
        panel.add(alterConditionsButton);

        alterConditionsButton.addActionListener(this);
        alterPlantButton.addActionListener(this);

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
            System.out.println("FUCK");
        }
    }
}
