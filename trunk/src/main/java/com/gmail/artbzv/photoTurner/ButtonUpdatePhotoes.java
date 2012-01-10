package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonUpdatePhotoes extends JButton implements ActionListener{

    ButtonUpdatePhotoes(){
        setText("<html>Обновить <p>фотки");
        setIcon(new ImageIcon("src\\resources\\icoRefresh32x32.png"));
        setPreferredSize(new Dimension(MainFrame.buttonsWidth, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        new Thread(new Updater()).start();
        
    }
}