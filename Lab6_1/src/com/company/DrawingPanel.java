package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 1000, H = 800;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image

    List<Shape> shapes = new ArrayList<>();

    public DrawingPanel(MainFrame frame) {
        this.frame = frame; createOffscreenImage(); init();
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }
    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(frame.controlPanel.getMode() == 0) {
                    drawShape(e.getX(), e.getY());
                    repaint();
                } else {
                    for(Shape sh : shapes) {
                        if(sh.contains(new Point2D.Double(e.getX(),e.getY()))){
                            shapes.remove(sh);
                            //redraw all shapes
                            repaint();
                        }
                    }
                }
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }


    private void drawShape(int x, int y) {
        Random rand = new Random();
        int width = (Integer) frame.configPanel.widthField.getValue();
        int sides = (Integer) frame.configPanel.sidesField.getValue();
        String col = (String) frame.configPanel.colorCombo.getSelectedItem();
        Color color;
        if(col.equals("Black"))
            color = new Color(0, 0, 0,0.5f );
        else
            color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(),0.5f );
        graphics.setColor(color);
        Shape s;
        switch (frame.configPanel.getConfigMode()){
            case 0:
                s = new RegularPolygon(x, y, width, sides);
                shapes.add(s);
                graphics.fill(s);
                break;
            case 1:
                int height = (Integer) frame.configPanel.heightField.getValue() * 2;
                int angle = (Integer) frame.configPanel.angleField.getValue();
                s = new SemiCircle(x, y, width, height, angle).getShape();
                shapes.add(s);
                graphics.fill(s);
                break;
            case 2:
                height = (Integer) frame.configPanel.heightField.getValue();
                s = new Rectangle(x-width/2,y-height/2,width,height);
                shapes.add(s);
                graphics.fill(s);
        }
    }
    @Override
    public void update(Graphics g) { } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

}
