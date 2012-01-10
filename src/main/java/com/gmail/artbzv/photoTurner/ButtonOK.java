package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonOK extends JButton implements ActionListener {

    ButtonOK(){
        setText("ОК");
        setIcon(new ImageIcon("src\\resources\\icoOk16x16.png"));
        setPreferredSize(new Dimension(SettingsFrame.buttonsWidth, SettingsFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        GlobalData.settingsFrame.applySettings();
        GlobalData.settingsFrame.hideSettings();

    }
}
