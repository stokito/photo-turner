package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ButtonAddPhotoes extends JButton implements ActionListener{

    ButtonAddPhotoes(){
        setText("<html>Добавить <p>фотки");

        ClassLoader cldr = this.getClass().getClassLoader();
        URL imageUrl = cldr.getResource("icoAdd32x32.png");
        setIcon(new ImageIcon(imageUrl));

        setPreferredSize(new Dimension(MainFrame.buttonsWidth, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        new Thread(new Loader()).start();

    }
}