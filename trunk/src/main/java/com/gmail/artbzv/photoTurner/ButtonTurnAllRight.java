package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTurnAllRight extends JButton implements ActionListener {

    ButtonTurnAllRight(){
        setIcon(new ImageIcon("icoRotateRight32x32.png"));
        setPreferredSize(new Dimension(MainFrame.buttonsHeight, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        GlobalData.photoCollection.turnRight();

    }
}