package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.WHITE;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    final JFileChooser fc = new JFileChooser();

    private int mode = 0;

    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    JButton selectBtn = new JButton("Select");
    JButton drawBtn = new JButton("Draw");
    JButton deleteBtn = new JButton("Delete Shape");

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        setLayout(new GridLayout(1, 6));

        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
        deleteBtn.addActionListener(this::deleteShape);
        drawBtn.addActionListener(this::drawShape);
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        add(drawBtn);
        add(deleteBtn);
    }
    private void save(ActionEvent e) {
        try {
            int returnVal = fc.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                ImageIO.write(frame.canvas.image, "PNG", file);
            }
        } catch (IOException ex) { System.err.println(ex); }
    }
    private void load(ActionEvent e) {
        try {
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.

                BufferedImage image = ImageIO.read(file);
                frame.canvas.graphics.drawImage(image, 0, 0, this);
                frame.canvas.repaint();
            }
        } catch (IOException ex) { System.err.println(ex); }
    }
    private void reset(ActionEvent e) {
            frame.canvas.graphics.setColor(WHITE);
            frame.canvas.graphics.fillRect(0,0, DrawingPanel.W, DrawingPanel.H);
            frame.canvas.repaint();
    }
    private void exit(ActionEvent e) {
        if (frame.isDisplayable()) {
            frame.dispose();
        }
    }

    private void drawShape(ActionEvent e) {
        mode = 0;
    }

    private void deleteShape(ActionEvent e) {
        mode = 1;
    }

    public int getMode() {
        return mode;
    }
}

