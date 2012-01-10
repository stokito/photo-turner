package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSettings extends JButton implements ActionListener{

    ButtonSettings(){
        setText("Настройки");
        setIcon(new ImageIcon("src\\resources\\icoSettings32x32.png"));
        setPreferredSize(new Dimension(MainFrame.buttonsWidth, MainFrame.buttonsHeight));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        GlobalData.settingsFrame.showSettings();
    }
}