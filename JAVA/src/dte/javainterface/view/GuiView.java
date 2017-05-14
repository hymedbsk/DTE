/*
 * GNU General Public License v3 (GPL-3)
 *
 * Copyright 2017
 * Christophe Van Waesberghe <contact@chrisv.be>
 * Amélie Courtin <a.courtin@students.ephec.be>
 * Mathieu Rousseau <m.rousseau@students.ephec.be>
 * Nathan Dolinsky <n.dolinski@students.ephec.be>
 * (Groupe 05, 2016/17)
 * 
 * The DTE Java Interface is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The DTE Java Interface is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the DTE Java Interface and the whole DTE Project.  If not, see <http://www.gnu.org/licenses/>.
 */
package dte.javainterface.view;

import dte.javainterface.controller.Controller;
import dte.javainterface.model.Model;
import static dte.javainterface.model.Model.ALERT_COOLING;
import static dte.javainterface.model.Model.ALERT_HEATING;
import static dte.javainterface.model.Model.ALERT_IDLE;
import static dte.javainterface.model.Model.ALERT_OVERHEATING;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Color;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 * Graphical interface of the App
 */
public class GuiView extends View {

    /**
     * GuiView simple constructor. Init the GUI
     */
    public GuiView() {
        initComponents();
        alertLabel.setVisible(false);
    }

    /**
     * GuiView's constructor with a Controller and a Model
     *
     * @param controller Controller of this view
     * @param model Model of this view
     */
    public GuiView(Controller controller, Model model) {
        super(controller, model);
        initComponents();
        alertLabel.setVisible(false);

    }

    /**
     * Update the view. Triggered when the Model change
     *
     * @param o Observable Object
     * @param arg Object the argument
     */
    @Override
    public void update(Observable o, Object arg) {

        if (super.model.isConnected()) {
            newThresold.setEnabled(true);
            newThresoldButton.setEnabled(true);
            comBox.setEnabled(false);
            comButton.setEnabled(false);
            status.setForeground(Color.GREEN);
            status.setText("Connected");

            if (super.model.getCurrentTemperature() == -1000) {
                currentTemperature.setText("N/A");
            } else {
                currentTemperature.setText(String.valueOf(super.model.getCurrentTemperature())+"° C");
            }
            if (super.model.getAlertLevel() == -1000) {
                alertLevel.setText("N/A");
            } else {
                alertLevel.setText(String.valueOf(super.model.getAlertLevel()));
            }
            if(this.model.getThresholdTemperature()==-1000){
                newThresold.setText("");
            }
            else{
            newThresold.setText(String.valueOf(this.model.getThresholdTemperature()));
            }
            printAlertLevel();
        } else {
            status.setForeground(Color.RED);
            status.setText("Disconnected");
            comBox.setEnabled(true);
            comButton.setEnabled(true);
            
            currentTemperature.setText("N/A");
            newThresoldButton.setEnabled(false);
            newThresold.setEnabled(false);
            alertLevel.setText("N/A");
            
            
            while(this.model.getEnumComm().hasMoreElements())
		{
			this.model.setSerialPortId((CommPortIdentifier)this.model.getEnumComm().nextElement());
			if(this.model.getSerialPortId().getPortType() == CommPortIdentifier.PORT_SERIAL)
			{
                            //if(!this.model.getSerialPortId().getName().contains("Bluetooth")){
                            
			comBox.addItem(this.model.getSerialPortId().getName());
                            //}
			}
		}
            
            
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    @Override
    public void printAlertLevel(){
        switch(this.model.getAlertLevel()){
            case ALERT_COOLING:
                alertLevel.setText("Cooling");
                break;
            case ALERT_IDLE:
                alertLevel.setText("IDLE");
                break;
            case ALERT_HEATING:
                alertLevel.setText("HEATING");
                break;
            case ALERT_OVERHEATING:
                alertLevel.setText("OVERHEATING");
                break;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        currentTempLabal = new javax.swing.JLabel();
        currentTemperature = new javax.swing.JLabel();
        alertLevelLabel = new javax.swing.JLabel();
        alertLevel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        comBox = new javax.swing.JComboBox<>();
        comButton = new javax.swing.JToggleButton();
        newThresold = new javax.swing.JTextField();
        newThresoldButton = new javax.swing.JToggleButton();
        alertLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        currentTempLabal.setText("Temperature :");

        currentTemperature.setText("N/A");

        alertLevelLabel.setText("Alert Level :");

        alertLevel.setText("N/A");

        statusLabel.setText("Status :");

        status.setText("Disconected");

        comBox.setToolTipText("");
        comBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comBoxActionPerformed(evt);
            }
        });

        comButton.setText("Connect");
        comButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comButtonActionPerformed(evt);
            }
        });

        newThresold.setText("20");
        newThresold.setToolTipText("The thresold temperature");
        newThresold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newThresoldActionPerformed(evt);
            }
        });

        newThresoldButton.setText("Apply");
        newThresoldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newThresoldButtonActionPerformed(evt);
            }
        });

        alertLabel.setText("New thresold applied");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(currentTempLabal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(currentTemperature))
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(alertLevelLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(alertLevel)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(statusLabel)
                        .addGap(37, 37, 37)
                        .addComponent(status)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newThresold))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newThresoldButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(alertLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentTempLabal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentTemperature)
                    .addComponent(comBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alertLevelLabel)
                    .addComponent(alertLevel)
                    .addComponent(newThresold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newThresoldButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(status)
                    .addComponent(alertLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void newThresoldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newThresoldButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newThresoldButtonActionPerformed

    private void newThresoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newThresoldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newThresoldActionPerformed

    private void comButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comButtonActionPerformed
        
        this.controller.connect(String.valueOf(comBox.getSelectedItem()));
        
    }//GEN-LAST:event_comButtonActionPerformed

    private void comBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel alertLabel;
    private javax.swing.JLabel alertLevel;
    private javax.swing.JLabel alertLevelLabel;
    private javax.swing.JComboBox<String> comBox;
    private javax.swing.JToggleButton comButton;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JLabel currentTempLabal;
    private javax.swing.JLabel currentTemperature;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField newThresold;
    private javax.swing.JToggleButton newThresoldButton;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JLabel status;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}
