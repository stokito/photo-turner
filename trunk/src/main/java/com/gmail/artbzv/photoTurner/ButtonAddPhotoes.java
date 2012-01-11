package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAddPhotoes extends JButton implements ActionListener{

    ButtonAddPhotoes(){
        setText("<html>Добавить <p>фотки");
      //setIcon(new ImageIcon("src\\main\\resources\\icoAdd32x32.png"));
        setIcon(new ImageIcon("icoAdd32x32.png"));
        setPreferredSize(new Dimension(MainFrame.buttonsWidth, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        new Thread(new Loader()).start();

    }
}