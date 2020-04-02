package com.company;

import javax.swing.*;

import java.awt.*;

import static java.awt.BorderLayout.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    ShapeListPanel shapeListPanel;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        shapeListPanel = new ShapeListPanel(this);
        //create the components
        canvas = new DrawingPanel(this);
 //...TODO
        add(configPanel, SOUTH);
        add(shapeListPanel, EAST);
        add(controlPanel, NORTH);
        //arrange the components in the container (frame)
        //JFrame uses a BorderLayout by default
        add(canvas, BorderLayout.CENTER); //this is BorderLayout.CENTER
 //...TODO

        //invoke the layout manager
        pack();
    }
}