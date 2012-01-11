package com.gmail.artbzv.photoTurner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

public class MainFrame extends JFrame{

    private String defaultTitle = "Поворачивайте фотки: правая клавиша - по часовой, левая - против";
    private JPanel photoesPane = new JPanel(null);
    private JScrollPane scrp = new JScrollPane(photoesPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public static int buttonsWidth = 140;
    public static int buttonsHeight = 40;

    private ComponentListener thisComponentListener = new ComponentListener(){

            public void componentResized(ComponentEvent e) {
                repaintMainFrame();
            }

            public void componentMoved(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
            public void componentHidden(ComponentEvent e) {}
        };

    private MouseListener photoIconMouseListener = new MouseListener(){

        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getComponent();
            Photo photo = GlobalData.photoCollection.get(label.getName());

            if(label.getComponentAt(e.getPoint()) == label.getComponent(0)){
                GlobalData.photoCollection.remove(label.getName());
                GlobalData.mainFrame.repaintPhotoPane();
            }

            if(thisIsRightButton(e.getButton())){
                photo.turnRight();
            }else{
                photo.turnLeft();
            }
            label.setIcon(photo.getIcon());
            label.repaint();
        }

        private boolean thisIsRightButton(int button) {

            boolean thisIsRightButton;

            if(GlobalData.mouseButtonsAreSwept){
                thisIsRightButton = (button == MouseEvent.BUTTON1);
            }else{
                thisIsRightButton = (button == MouseEvent.BUTTON3);
            }

            return thisIsRightButton;

        }

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {}

        public void mouseEntered(MouseEvent e) {
            JLabel label = (JLabel) e.getComponent();
            label.getComponent(0).setVisible(true);
        }

        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getComponent();
            label.getComponent(0).setVisible(false);
        }
        
    };

    MainFrame(){

        super();

        add(createButtonPanel(), BorderLayout.NORTH);

        photoesPane.setPreferredSize(new Dimension(200,200));
        add(scrp, BorderLayout.CENTER);

        add(createAuthorInfoPannel(), BorderLayout.SOUTH);

        setSize(850,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addComponentListener(thisComponentListener);

    }

    private JPanel createAuthorInfoPannel() {

        JPanel authorInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel authorInfoLabel = new JLabel("<html> Made by Artem Urkov <a href=\"mailto:artbzv@gmail.com\"> artbzv@gmail.com</a>");
        authorInfoPanel.add(authorInfoLabel);

        return authorInfoPanel;

    }

    private JPanel createButtonPanel() {

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(new ButtonAddPhotoes());
        buttonPanel.add(new ButtonSaveChanges());
        buttonPanel.add(new ButtonClearAll());
        buttonPanel.add(new ButtonUpdatePhotoes());
        buttonPanel.add(new ButtonSettings());
        buttonPanel.add(new ButtonTurnAllLeft());
        buttonPanel.add(new ButtonTurnAllRight());

        return buttonPanel;

    }

    void repaintMainFrame() {
        restoreTitle();
        repaintPhotoPane();
    }

    private void restoreTitle() {
        setTitle(defaultTitle);
    }

    public void repaintPhotoPane() {

        photoesPane.removeAll();

        int gapBetweenLabels = GlobalData.gapBetweenLabels;
        int picPerRow = (scrp.getViewport().getSize().width - gapBetweenLabels)/(GlobalData.imageWidth+gapBetweenLabels);
        int row = 1;
        int col = 0;
        Set<String> keys = GlobalData.photoCollection.keySet();

        for (String key:keys) {

            Photo photo = GlobalData.photoCollection.get(key);

            JLabel photoLabel = new JLabel();
            photoLabel.setName(key);
            photoLabel.setHorizontalAlignment(JLabel.CENTER);
            photoLabel.setIcon(photo.getIcon());
            photoLabel.setToolTipText(photo.getToolTipText());

            if (col == picPerRow) {
                col = 1;
                row++;
            } else {
                col++;
            }

            int x = gapBetweenLabels + (col - 1) * (GlobalData.imageWidth + gapBetweenLabels);
            int y = gapBetweenLabels + (row - 1) * (GlobalData.imageHeight + gapBetweenLabels);
            photoLabel.setLocation(x, y);
            photoLabel.setSize(GlobalData.imageWidth, GlobalData.imageHeight);
            photoLabel.setOpaque(true);
            photoLabel.addMouseListener(photoIconMouseListener);
            photoesPane.add(photoLabel);

            JLabel closePhotoButton = new JLabel();

            ClassLoader cldr = this.getClass().getClassLoader();
            URL imageUrl = cldr.getResource("icoClose32x32.png");
            closePhotoButton.setIcon(new ImageIcon(imageUrl));

            closePhotoButton.setSize(20,20);
            closePhotoButton.setLocation(photoLabel.getWidth() - closePhotoButton.getWidth(), 0);
            closePhotoButton.setVisible(false);

            photoLabel.add(closePhotoButton);

        }

        photoesPane.setPreferredSize(new Dimension(gapBetweenLabels + (col)*(GlobalData.imageWidth+gapBetweenLabels), gapBetweenLabels + (row)*(GlobalData.imageHeight+gapBetweenLabels)));
        scrp.updateUI();
    }

}

