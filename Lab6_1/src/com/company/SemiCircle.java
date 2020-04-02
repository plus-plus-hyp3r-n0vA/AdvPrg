package com.company;

import java.awt.*;
import java.awt.geom.Arc2D;

public class SemiCircle {
    private Shape shape;
    public SemiCircle(int x0, int y0, int w, int h, int startAngle) {
        shape = new Arc2D.Double(x0-w/2.0, y0-h/2.0, w, h, startAngle, 180, Arc2D.OPEN);
    }

    public Shape getShape() {
        return shape;
    }
}
