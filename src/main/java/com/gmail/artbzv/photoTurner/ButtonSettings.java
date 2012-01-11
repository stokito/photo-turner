package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ButtonSettings extends JButton implements ActionListener{

    ButtonSettings(){
        setText("Настройки");

        ClassLoader cldr = this.getClass().getClassLoader();
        URL imageUrl = cldr.getResource("icoSettings32x32.png");
        setIcon(new ImageIcon(imageUrl));

        setPreferredSize(new Dimension(MainFrame.buttonsWidth, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        GlobalData.settingsFrame.showSettings();
    }
}