package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShapeListPanel extends JPanel {
    final MainFrame frame;
    JList list = new JList(new String[]{"regular polygon", "semi-circle", "rectangle"});

    public ShapeListPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        setLayout(new GridLayout(1, 1));
        list.setSelectedIndex(0);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this::selection);

        add(list);
    }
    private void selection(ListSelectionEvent e) {
        switch (list.getSelectedIndex()) {
            case 0:
                frame.configPanel.regularPolygonConfig();
                break;
            case 1:
                frame.configPanel.semiCircleConfig();
                break;
            case 2:
                frame.configPanel.rectangleConfig();
        }
    }
}


