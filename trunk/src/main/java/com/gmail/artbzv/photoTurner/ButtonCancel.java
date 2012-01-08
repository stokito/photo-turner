package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonCancel extends JButton implements ActionListener {

    ButtonCancel(){
        setText("Отмена");
        setIcon(new ImageIcon("icoCancel16x16.png"));
        setPreferredSize(new Dimension(SettingsFrame.buttonsWidth, SettingsFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        GlobalData.settingsFrame.restoreSettings();
        GlobalData.settingsFrame.hideSettings();
    }
}
