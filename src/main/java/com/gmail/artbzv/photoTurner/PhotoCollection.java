package com.gmail.artbzv.photoTurner;

import java.util.LinkedHashMap;

public class PhotoCollection extends LinkedHashMap<String,Photo> {

    public void clearIcons() {
        for(Photo photo : values()){
            photo.clearIcons();
        }
        GlobalData.mainFrame.repaintPhotoPane();  
        System.gc();
    }

    public void turnRight() {
        for(Photo photo : values()){
            photo.turnRight();
        }
        GlobalData.mainFrame.repaintPhotoPane();
    }

    public void turnLeft() {
        for(Photo photo : values()){
            photo.turnLeft();
        }
        GlobalData.mainFrame.repaintPhotoPane();
    }

    public boolean hasProblemsWithFiles() {

        boolean hasProblemsWithFiles = false;
        for(Photo photo : values()){
            hasProblemsWithFiles = hasProblemsWithFiles || photo.hasProblemWithFile();
        }

        return hasProblemsWithFiles;

    }
}
