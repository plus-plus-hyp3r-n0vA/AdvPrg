import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JTextField swComponentName = new JTextField(20);
    JTextField defaultText = new JTextField(20);
    JButton addButton = new JButton("Add");
    JTable table;

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }

    private void init(){
        addButton.addActionListener(this::addComponent);
        add(swComponentName);
        add(defaultText);
        add(addButton);
        repaint();
    }

    private void addComponent(ActionEvent e) {

        String compName = swComponentName.getText();
        try {
            Class clazz = Class.forName(compName);
            JComponent comp = (JComponent) clazz.getConstructor().newInstance();
            try {
                Method method = clazz.getMethod("setText", String.class);
                method.invoke(comp, defaultText.getText());
            } catch (NoSuchMethodException ignored) { }
            try {
                var focLis = clazz.getMethod("addFocusListener", FocusListener.class);
                focLis.invoke(comp, new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        BeanInfo info = null;
                        try {
                            info = Introspector.getBeanInfo(clazz);
                        } catch (IntrospectionException introspectionException) {
                            introspectionException.printStackTrace();
                        }
                        Object[][] data = new Object[info.getPropertyDescriptors().length][1];
                        int i=0;
                        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                            data[i][0] = pd.getName();
                            ++i;
                        }
                        table = new JTable(data, new String[]{"Properties"});
                        add(table);
                        frame.repaint();

                    }

                    @Override
                    public void focusLost(FocusEvent e) {

                    }
                });
            } catch (NoSuchMethodException ignored) { }
            frame.designPanel.addRandPosition(comp);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException ex) {
            ex.printStackTrace();
        }

    }
}
