package com.gmail.artbzv.photoTurner;

import java.io.File;
import java.io.FileFilter;

public class ImageFileFilter implements FileFilter {

    public boolean accept(File pathname) {
        if(pathname != null && pathname.isFile()){
            String name = pathname.getName();
            int i = name.lastIndexOf('.');
            if(i>0 && i < name.length()-1){
                String extention = name.substring(i + 1);
                if (extention.equalsIgnoreCase("jpg")){
                    return true;
                }else
                if (extention.equalsIgnoreCase("jpeg")){
                    return true;
                }
            }
        }
        return false;
    }
}
