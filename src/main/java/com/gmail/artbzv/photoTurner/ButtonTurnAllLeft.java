package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ButtonTurnAllLeft extends JButton implements ActionListener {

    ButtonTurnAllLeft(){
        ClassLoader cldr = this.getClass().getClassLoader();
        URL imageUrl = cldr.getResource("icoRotateLeft32x32.png");
        setIcon(new ImageIcon(imageUrl));

        setPreferredSize(new Dimension(MainFrame.buttonsHeight, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        GlobalData.photoCollection.turnLeft();

    }
}