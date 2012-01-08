package com.gmail.artbzv.photoTurner;

import javax.swing.*;


public class Saver implements Runnable {

    public void run() {

        GlobalData.deactivateFrames("Подождите идет сохранение фотографий...");

        GlobalData.photoCollection.clearIcons();

        int numOfPhotoes = GlobalData.photoCollection.size();
        int i = 0;
        ProgressMonitor progressMonitor = new ProgressMonitor(GlobalData.mainFrame, "Идет сохранение фоток", "Осталось", 0, numOfPhotoes);
        progressMonitor.setMillisToDecideToPopup(0);

        String status = "успешно сохранены!";
        for (Photo photo : GlobalData.photoCollection.values()) {

            if(progressMonitor.isCanceled()){
                status = "частично сохранены!";
                break;
            }

            progressMonitor.setProgress(i);
            progressMonitor.setNote((i+1) + " из " + numOfPhotoes);

            photo.saveChanges();

            i++;
        }

        progressMonitor.setProgress(numOfPhotoes);

        if(GlobalData.photoCollection.hasProblemsWithFiles()){
            status = "сохранены с ошибками!";
        }

        int response = JOptionPane.showConfirmDialog(GlobalData.mainFrame,"Изменения " + status + "\nПерезагрузить сохраненные фотографии?","Вопрос",JOptionPane.YES_NO_OPTION);

        if(response == JOptionPane.YES_OPTION){
            new Thread(new Updater()).start();
        } else {
            GlobalData.photoCollection.clear();
            GlobalData.activateFrames();
        }


    }
}