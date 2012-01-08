package com.gmail.artbzv.photoTurner;

import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReaderSpi;
import com.sun.imageio.plugins.jpeg.JPEGImageWriter;
import com.sun.imageio.plugins.jpeg.JPEGImageWriterSpi;

import javax.imageio.*;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;

public class Photo {

    private ImageIcon upIcon;
    private ImageIcon rightIcon;
    private ImageIcon downIcon;
    private ImageIcon leftIcon;

    private File file;
    private boolean hasProblemWithFile;

    private PhotoPosition photoPosition;
    private String toolTipText;

    public void upload() {
        upload(false);
    }

    public void upload(boolean savePhotoPosition) {

        BufferedImage bigImage = readFromFile();
        BufferedImage upImage = adaptImageToIcon(bigImage);
        setIconsAndToolTipText(upImage);

        if(!savePhotoPosition){
            photoPosition = PhotoPosition.Up;
        }

    }

    private void setIconsAndToolTipText(BufferedImage upImage){

        if(upImage == null){
            Image errorImage = Toolkit.getDefaultToolkit().createImage("icoLoadError300x300.png");
            ImageIcon errorIcon = new ImageIcon(errorImage.getScaledInstance(GlobalData.imageWidth, GlobalData.imageHeight, Image.SCALE_SMOOTH));
            upIcon      = errorIcon;
            rightIcon   = errorIcon;
            downIcon    = errorIcon;
            leftIcon    = errorIcon;
            toolTipText = file.getAbsolutePath();
        } else {
            upIcon      = new ImageIcon(upImage);
            rightIcon   = new ImageIcon(turnImage(upImage,PhotoPosition.Right));
            downIcon    = new ImageIcon(turnImage(upImage,PhotoPosition.Down));
            leftIcon    = new ImageIcon(turnImage(upImage,PhotoPosition.Left));
            toolTipText = null;
        }

    }

    public void clearIcons() {
        upIcon      = null;
        rightIcon   = null;
        downIcon    = null;
        leftIcon    = null;
    }

    public String getToolTipText() {
        return toolTipText;
    }

    public boolean hasProblemWithFile() {
        return hasProblemWithFile;
    }

    enum PhotoPosition{Up(0),Right(1),Down(2),Left(3);

        PhotoPosition(int i) {}

        PhotoPosition turnRight(){
            int value = (ordinal() + 1)%4;
            return values()[value];
        }

        PhotoPosition turnLeft(){
            int value = (ordinal() + 3)%4;
            return values()[value];
        }

    }

    Photo(File file){

        this.file = file;

        upload();

    }

    private BufferedImage adaptImageToIcon(BufferedImage sourceImage) {

        if(sourceImage == null){
            return null;
        }

        Dimension destinationImageDimension = calculateScaledDimension(sourceImage);

        int destinationImageHeight  = (int) destinationImageDimension.getHeight();
        int destinationImageWidth   = (int) destinationImageDimension.getWidth();

        Image destinationImage = sourceImage.getScaledInstance(destinationImageWidth, destinationImageHeight, Image.SCALE_SMOOTH);

        BufferedImage destinationBufferedImage = new BufferedImage(destinationImageWidth,destinationImageHeight,BufferedImage.TYPE_INT_RGB);
        destinationBufferedImage.getGraphics().drawImage(destinationImage,0,0,null);

        return destinationBufferedImage;
    }

    private Dimension calculateScaledDimension(BufferedImage sourceImage) {

        int sourceWidth     = sourceImage.getWidth();
        int sourceHeight    = sourceImage.getHeight();

        boolean sourceImageIsVertical = (sourceHeight >= sourceWidth);

        int scaledWidth;
        int scaledHeight;

        if(sourceImageIsVertical){
            scaledWidth     = sourceWidth * GlobalData.imageHeight / sourceHeight;
            scaledHeight    = GlobalData.imageHeight;
        } else {
            scaledWidth     = GlobalData.imageWidth;
            scaledHeight    = sourceHeight * GlobalData.imageWidth / sourceWidth;
        }

        return new Dimension(scaledWidth, scaledHeight);

    }

    ImageIcon getIcon(){

        switch (photoPosition){
            case Up: return upIcon;
            case Right: return rightIcon;
            case Down: return downIcon;
            case Left: return leftIcon;
        }
        return null;

    }

    void turnRight(){
        photoPosition = photoPosition.turnRight();
    }

    void turnLeft(){
        photoPosition = photoPosition.turnLeft();
    }

    BufferedImage readFromFile() {

        hasProblemWithFile = false;
        ImageReaderSpi spi = new JPEGImageReaderSpi();
        ImageReader imageReader = new JPEGImageReader(spi);

        try {

            FileImageInputStream fileImageInputStream = new FileImageInputStream(file);
            imageReader.setInput(fileImageInputStream);
            BufferedImage buffImage = imageReader.read(0, new ImageReadParam());
            fileImageInputStream.close();

            return buffImage;

        } catch (Exception e) {
            hasProblemWithFile = true;
            return null;
        }

    }

    private void writeToFile(BufferedImage bufIm){

        try {
            IIOImage iioImage = new IIOImage(bufIm, null, null);
            JPEGImageWriterSpi spi = new JPEGImageWriterSpi();
            ImageWriter imageWriter = new JPEGImageWriter(spi);

            FileImageOutputStream fileImageOutputStream = new FileImageOutputStream(file);
            imageWriter.setOutput(fileImageOutputStream);

            ImageWriteParam iwp = imageWriter.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(GlobalData.compressionMode.getCompression());

            imageWriter.write(null, iioImage, iwp);
            fileImageOutputStream.flush();
            fileImageOutputStream.close();

        } catch (Exception e) {
            hasProblemWithFile = true;
            setIconsAndToolTipText(null);
        }

    }

    void saveChanges(){

        if(photoPosition == PhotoPosition.Up && GlobalData.compressionMode == GlobalData.CompressionMode.SaveSize){
            return;
        }

        BufferedImage sourceBufferedImage = readFromFile();
        BufferedImage targetBufferedImage = turnImage(sourceBufferedImage, photoPosition);
        writeToFile(targetBufferedImage);

        //setIconsAndToolTipText(getBufferedImageFromIcon(getIcon()));

    }

//    BufferedImage getBufferedImageFromIcon(ImageIcon imageIcon){
//
//        int width   = imageIcon.getImage().getWidth(imageIcon.getImageObserver());
//        int height  = imageIcon.getImage().getHeight(imageIcon.getImageObserver());
//
//        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        bufferedImage.getGraphics().drawImage(imageIcon.getImage(),0,0,null);
//
//        return bufferedImage;
//
//    }

    BufferedImage turnImage(BufferedImage sourceImage, PhotoPosition photoPosition) {

        if(sourceImage == null){
            return null;
        }

        if(photoPosition == PhotoPosition.Up){
            return sourceImage;
        }

        Boolean shouldBeTurned = (photoPosition == PhotoPosition.Left || photoPosition == PhotoPosition.Right);

        int destinationHeight;
        int destinationWidth;
        if(shouldBeTurned){
            destinationHeight   = sourceImage.getWidth();
            destinationWidth    = sourceImage.getHeight();
        }else{
            destinationHeight   = sourceImage.getHeight();
            destinationWidth    = sourceImage.getWidth();
        }

        BufferedImage destinationImage = new BufferedImage(destinationWidth, destinationHeight, BufferedImage.TYPE_INT_RGB);

        AffineTransform turnAffineTransform = AffineTransform.getQuadrantRotateInstance(photoPosition.ordinal(),0,0);
        AffineTransform shiftAffineTransform = null;
        switch(photoPosition){
            case Right:
                shiftAffineTransform = AffineTransform.getTranslateInstance(sourceImage.getHeight(),0);
                break;
            case Down:
                shiftAffineTransform = AffineTransform.getTranslateInstance(sourceImage.getWidth(),sourceImage.getHeight());
                break;
            case Left:
                shiftAffineTransform = AffineTransform.getTranslateInstance(0,sourceImage.getWidth());
                break;
        }

        turnAffineTransform.preConcatenate(shiftAffineTransform);

        Graphics2D g2d = (Graphics2D)destinationImage.getGraphics();
        g2d.setTransform(turnAffineTransform);
        g2d.drawImage(sourceImage,0,0,null);

        return destinationImage;
    }



}
