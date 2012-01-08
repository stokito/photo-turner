package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClearAll extends JButton implements ActionListener{

    ButtonClearAll(){
        setText("<html>Очистить <p> все");
        setIcon(new ImageIcon("icoClear32x32.png"));
        setPreferredSize(new Dimension(MainFrame.buttonsWidth, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        GlobalData.photoCollection.clear();
        GlobalData.mainFrame.repaintMainFrame();
    }
}