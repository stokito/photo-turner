package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonApply extends JButton implements ActionListener {

    public ButtonApply(){
        setText("Применить");
        setIcon(new ImageIcon("classpath:icoApply16x16.png"));
        setPreferredSize(new Dimension(SettingsFrame.buttonsWidth, SettingsFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        GlobalData.settingsFrame.applySettings();
    }
}
