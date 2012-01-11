package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ButtonUpdatePhotoes extends JButton implements ActionListener{

    ButtonUpdatePhotoes(){
        setText("<html>Обновить <p>фотки");

        ClassLoader cldr = this.getClass().getClassLoader();
        URL imageUrl = cldr.getResource("icoRefresh32x32.png");
        setIcon(new ImageIcon(imageUrl));

        setPreferredSize(new Dimension(MainFrame.buttonsWidth, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        new Thread(new Updater()).start();
        
    }
}