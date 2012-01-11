package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ButtonOK extends JButton implements ActionListener {

    ButtonOK(){
        setText("ОК");

        ClassLoader cldr = this.getClass().getClassLoader();
        URL imageUrl = cldr.getResource("icoOk16x16.png");
        setIcon(new ImageIcon(imageUrl));

        setPreferredSize(new Dimension(SettingsFrame.buttonsWidth, SettingsFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        GlobalData.settingsFrame.applySettings();
        GlobalData.settingsFrame.hideSettings();

    }
}
