package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ButtonCancel extends JButton implements ActionListener {

    ButtonCancel(){
        setText("Отмена");

        ClassLoader cldr = this.getClass().getClassLoader();
        URL imageUrl = cldr.getResource("icoCancel16x16.png");
        setIcon(new ImageIcon(imageUrl));
        
        setPreferredSize(new Dimension(SettingsFrame.buttonsWidth, SettingsFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        GlobalData.settingsFrame.restoreSettings();
        GlobalData.settingsFrame.hideSettings();
    }
}
