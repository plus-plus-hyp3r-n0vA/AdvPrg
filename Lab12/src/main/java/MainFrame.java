import javax.swing.*;

import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;

    public MainFrame() {
        super("Dynamic Swing Designer");
//        rootPane.setPreferredSize(new Dimension(1200, 700));
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this);

        add(controlPanel, NORTH);
        add(designPanel, CENTER);

        pack();
    }
}
