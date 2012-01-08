package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTurnAllLeft extends JButton implements ActionListener {

    ButtonTurnAllLeft(){
        setIcon(new ImageIcon("icoRotateLeft32x32.png"));
        setPreferredSize(new Dimension(MainFrame.buttonsHeight, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        GlobalData.photoCollection.turnLeft();

    }
}