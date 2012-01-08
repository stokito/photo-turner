package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;


public class SettingsFrame extends JFrame{

    private JSpinner photoesSize;
    private JSpinner photoesGapSize;
    private JComboBox compressionMode;
    private JCheckBox mouseButtonsAreSwept;

    public static int buttonsHeight = 20;
    public static int buttonsWidth = 120;


    SettingsFrame(){

        super("Настройки");

        add(makeMainPanel(), BorderLayout.CENTER);
        add(makeButtonPanel(), BorderLayout.SOUTH);
        restoreSettings();

        setResizable(false);
        setSize(400,200);
        setVisible(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

    }

    private JPanel makeMainPanel(){

            JPanel mainPanel = new JPanel(new GridBagLayout());

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            gridBagConstraints.insets = new Insets(5,10,5,10);

                gridBagConstraints.gridy = 0;

                    gridBagConstraints.anchor = GridBagConstraints.WEST;
                    JLabel photoesSizeLabel = new JLabel("Размер фоток");
                    mainPanel.add(photoesSizeLabel, gridBagConstraints);

                    gridBagConstraints.anchor = GridBagConstraints.EAST;
                    photoesSize = new JSpinner(new SpinnerNumberModel(GlobalData.imageWidth,50,300,25));
                    mainPanel.add(photoesSize, gridBagConstraints);

                gridBagConstraints.gridy++;

                    gridBagConstraints.anchor = GridBagConstraints.WEST;
                    JLabel photoesGapSizeLabel = new JLabel("Расстояние между ними");
                    mainPanel.add(photoesGapSizeLabel, gridBagConstraints);

                    gridBagConstraints.anchor = GridBagConstraints.EAST;
                    photoesGapSize = new JSpinner(new SpinnerNumberModel(GlobalData.gapBetweenLabels,1,50,5));
                    mainPanel.add(photoesGapSize, gridBagConstraints);

                gridBagConstraints.gridy++;

                    gridBagConstraints.anchor = GridBagConstraints.WEST;
                    JLabel compressionModeLabel = new JLabel("Режим сжатия");
                    mainPanel.add(compressionModeLabel, gridBagConstraints);

                    gridBagConstraints.anchor = GridBagConstraints.EAST;
                    compressionMode = new JComboBox(GlobalData.CompressionMode.values());
                    mainPanel.add(compressionMode, gridBagConstraints);

                gridBagConstraints.gridy++;

                    gridBagConstraints.anchor = GridBagConstraints.WEST;
                    JLabel mouseButtonsAreSweptLabel = new JLabel("Я левша");
                    mainPanel.add(mouseButtonsAreSweptLabel, gridBagConstraints);

                    gridBagConstraints.anchor = GridBagConstraints.EAST;
                    mouseButtonsAreSwept = new JCheckBox();
                    mainPanel.add(mouseButtonsAreSwept, gridBagConstraints);

            return mainPanel;

        }

    private JPanel makeButtonPanel() {

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(new ButtonOK());
        buttonPanel.add(new ButtonCancel());
        buttonPanel.add(new ButtonApply());

        return buttonPanel;

    }

    void showSettings(){
        setVisible(true);
    }

    void hideSettings(){
        setVisible(false);
    }

    void applySettings(){

        boolean isNecessaryPhotoReload = (GlobalData.imageHeight != (Integer) photoesSize.getValue());
        
        GlobalData.compressionMode = (GlobalData.CompressionMode) compressionMode.getSelectedItem();
        GlobalData.imageHeight = (Integer) photoesSize.getValue();
        GlobalData.imageWidth = (Integer) photoesSize.getValue();
        GlobalData.gapBetweenLabels = (Integer) photoesGapSize.getValue();
        GlobalData.mouseButtonsAreSwept = mouseButtonsAreSwept.isSelected();

        if(isNecessaryPhotoReload){
            Updater updater = new Updater();
            updater.setSavePhotoPosition(true);
            new Thread(updater).start();
        }else{
            GlobalData.mainFrame.repaintMainFrame();
        }
        
    }

    void restoreSettings(){

        compressionMode.setSelectedItem(GlobalData.compressionMode);
        photoesSize.setValue(GlobalData.imageHeight);
        photoesGapSize.setValue(GlobalData.gapBetweenLabels);
        mouseButtonsAreSwept.setSelected(GlobalData.mouseButtonsAreSwept);

    }

}