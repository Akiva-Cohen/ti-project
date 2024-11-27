import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
public class PageMaker extends JOptionPane {
    static JPanel panel;
    static JButton addButton;
    static JList<Button> buttonList;
    static {
        panel = new JPanel(new GridLayout(2,1));
        JTextArea box = new JTextArea(10, 26);
        box.setLineWrap(true);
        panel.add(box); 
        JPanel buttonManager = new JPanel(new GridLayout(1,2));
        buttonList = new JList<>();
        buttonList.setSelectedIndex(0);
        addButton = new JButton("Add Button");
        buttonManager.add(buttonList);
        buttonManager.add(addButton);
        panel.add(buttonManager);
    }
    
    public static void promptNewPage() {
        DefaultListModel<Button> list = new DefaultListModel<Button>();
        buttonList = new JList<Button>(list);
        addButton.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                try {
                    list.add(0, Popup.promptNewButton());
                    buttonList = new JList<Button>(list);
                    buttonList.revalidate();
                    buttonList.repaint();
                    buttonList.ensureIndexIsVisible(0);
                    buttonList.setVisible(true);
                    buttonList.setVisibleRowCount(10);
                } catch (IllegalAccessError ee) {}
            }
        });
        showConfirmDialog(null, panel,"Make New Page", JOptionPane.OK_CANCEL_OPTION);
    }
    public static Button[] buttonListToArray(ArrayList<Button> list) {
        Button[] out = new Button[list.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = list.get(i);
        }
        return out;
    }
}