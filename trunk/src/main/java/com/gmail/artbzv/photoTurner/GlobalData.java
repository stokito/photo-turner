package com.gmail.artbzv.photoTurner;

public class GlobalData {

    public static MainFrame mainFrame;
    public static SettingsFrame settingsFrame;
    public static PhotoCollection photoCollection;

    public static int imageWidth = 100;
    public static int imageHeight = 100;

    public static int gapBetweenLabels = 5;

    public static boolean mouseButtonsAreSwept = true;

    public static CompressionMode compressionMode = CompressionMode.SaveSize;

    public static String startDirectory //= "g:\\ФотоТест\\";
                                        = "H:\\ФотоТест\\";

    enum CompressionMode{
        SaveSize(0.98f, "Сохранять размер файла"),
        Low(1f, "Низкая компрессия"),
        Medium(0.75f, "Средняя компрессия"),
        Hight(0.5f, "Высокая компрессия");

        private float compression;
        private String name;

        CompressionMode(float i, String name) {
            this.compression = i;
            this.name = name;
        }

        public float getCompression() {
            return compression;
        }

        public String toString(){
            return name;
        }

    }

    public static void main(String[] args) {
        photoCollection = new PhotoCollection();
        mainFrame = new MainFrame();
        settingsFrame = new SettingsFrame();
    }

    static void activateFrames(){
        mainFrame.setEnabled(true);
        settingsFrame.setEnabled(true);
        mainFrame.toFront();
        settingsFrame.toFront();
        mainFrame.repaintMainFrame();
    }

    static void deactivateFrames(String mainFrameTitle){
        mainFrame.setTitle(mainFrameTitle);
        mainFrame.setEnabled(false);
        settingsFrame.setEnabled(false);
    }

}
