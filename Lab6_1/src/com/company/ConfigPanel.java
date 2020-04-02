package com.company;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;

    private int configMode;

    JLabel sidesLabel, widthLabel, heightLabel, angleLabel;
    JSpinner sidesField, widthField, heightField, angleField;
    JComboBox colorCombo;
//    private JComponent[] regularPolygonConfig, semiCircleConfig;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
//        regularPolygonConfig = new JComponent[]{sidesField, sizeField, sidesLabel, sizeLabel, colorCombo};
//        semiCircleConfig = new JComponent[]{sidesField, sizeField, sidesLabel, sizeLabel, colorCombo};

        init();
    }
    private void init() {
        //create the label and the spinner
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(6, 0, 100, 1));

        widthLabel = new JLabel("Size of shape:");
        widthField = new JSpinner(new SpinnerNumberModel(100, 5, 500, 1));

        heightLabel = new JLabel("Height:");
        heightField = new JSpinner(new SpinnerNumberModel(50, 5, 500, 1));


        angleLabel = new JLabel("Rotation angle:");
        angleField = new JSpinner(new SpinnerNumberModel(0, 0, 360, 1));

        regularPolygonConfig();

        colorCombo = new JComboBox(new String[]{"Random", "Black"});

        add(sidesLabel);
        add(sidesField);
        add(widthLabel);
        add(widthField);
        add(heightLabel);
        add(heightField);
        add(angleLabel);
        add(angleField);
        add(colorCombo);
    }

    public void regularPolygonConfig()
    {
        configMode = 0;
        widthLabel.setText("Size of shape:");
        sidesLabel.setVisible(true);
        sidesField.setVisible(true);
        heightLabel.setVisible(false);
        heightField.setVisible(false);
        angleLabel.setVisible(false);
        angleField.setVisible(false);
    }

    public void semiCircleConfig()
    {
        configMode = 1;
        widthLabel.setText("Width:");
        sidesLabel.setVisible(false);
        sidesField.setVisible(false);
        heightLabel.setVisible(true);
        heightField.setVisible(true);
        angleLabel.setVisible(true);
        angleField.setVisible(true);
    }

    public void rectangleConfig()
    {
        configMode = 2;
        widthLabel.setText("Width:");
        sidesLabel.setVisible(false);
        sidesField.setVisible(false);
        heightLabel.setVisible(true);
        heightField.setVisible(true);
        angleLabel.setVisible(false);
        angleField.setVisible(false);
    }

    public int getConfigMode() {
        return configMode;
    }
}

