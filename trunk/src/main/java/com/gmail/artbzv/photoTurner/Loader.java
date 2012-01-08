package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.io.File;


public class Loader implements Runnable {

    public void run() {

        GlobalData.deactivateFrames("Подождите идет загрузка фотографий...");

        JFileChooser fd = new JFileChooser(GlobalData.startDirectory);
        fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int state = fd.showDialog(null,"Выбрать");
        File f = fd.getSelectedFile();
        if(f == null || state != JFileChooser.APPROVE_OPTION){
            GlobalData.activateFrames();
            return;
        }

        File[] files = f.listFiles(new ImageFileFilter());
        int numOfFiles = files.length;

        if (numOfFiles == 0) {
            GlobalData.activateFrames();
            return;
        }

        ProgressMonitor progressMonitor = new ProgressMonitor(GlobalData.mainFrame, "Идет загрузка фоток", "Осталось", 0, numOfFiles);
        progressMonitor.setMillisToDecideToPopup(0);

        for (int i = 0; i < numOfFiles; i++) {

            if(progressMonitor.isCanceled()){
                break;
            }

            progressMonitor.setProgress(i);
            progressMonitor.setNote((i+1) + " из " + numOfFiles);

            String fileName = files[i].getAbsolutePath();
            if(GlobalData.photoCollection.containsKey(fileName)){
                continue;
            }

            GlobalData.photoCollection.put(fileName, new Photo(files[i]));

        }

        progressMonitor.setProgress(numOfFiles);

        GlobalData.activateFrames();
        
    }
}
