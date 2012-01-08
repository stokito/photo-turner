package com.gmail.artbzv.photoTurner;

import javax.swing.*;


public class Updater implements Runnable {

    private boolean savePhotoPosition = false;

    public void run() {

        GlobalData.deactivateFrames("Подождите идет обновление фотографий...");

        int numOfPhotoes = GlobalData.photoCollection.size();
        int i = 0;
        ProgressMonitor progressMonitor = new ProgressMonitor(GlobalData.mainFrame, "Идет обновление фоток", "Обновление", 0, numOfPhotoes);
        progressMonitor.setMillisToDecideToPopup(0);

        for (Photo photo : GlobalData.photoCollection.values()) {

            progressMonitor.setProgress(i);
            progressMonitor.setNote((i+1) + " из " + numOfPhotoes);

            photo.upload(savePhotoPosition);

            i++;
        }

        progressMonitor.setProgress(numOfPhotoes);

        GlobalData.activateFrames();

    }

    public void setSavePhotoPosition(boolean savePhotoPosition) {
        this.savePhotoPosition = savePhotoPosition;
    }
}