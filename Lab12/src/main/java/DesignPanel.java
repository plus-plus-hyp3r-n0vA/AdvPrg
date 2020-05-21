import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DesignPanel extends JPanel {
    final MainFrame frame;
    private static final int width = 1000, height = 700;

    public DesignPanel(MainFrame frame) {
        this.frame = frame;

        setPreferredSize(new Dimension(width, height));
    }

    public void addRandPosition(JComponent component) {
        Random rand = new Random();
        component.setBounds(rand.nextInt(width), rand.nextInt(height),
                component.getPreferredSize().width, component.getPreferredSize().height);
        this.add(component);
        frame.repaint();
    }

}
