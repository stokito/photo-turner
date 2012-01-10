package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSaveChanges extends JButton implements ActionListener{

    ButtonSaveChanges(){
        setText("<html>Сохранить <p> изменения");
        setIcon(new ImageIcon("src\\resources\\icoSave32x32.png"));
        setPreferredSize(new Dimension(MainFrame.buttonsWidth, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {

        new Thread(new Saver()).start();

    }
}