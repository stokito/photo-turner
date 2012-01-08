package com.gmail.artbzv.photoTurner;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: msk5121
 * Date: 10.11.2011
 * Time: 23:13:22
 * To change this template use File | com.gmail.artbzv.photoTurner.GlobalData | File Templates.
 */
public class Warning {

    Warning(Object ob){
        JOptionPane.showMessageDialog(GlobalData.mainFrame, ob);
    }

}
