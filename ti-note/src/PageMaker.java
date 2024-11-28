import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
public class PageMaker extends JOptionPane {
    static JPanel panel;
    static JButton addButton;
    static JList<Button> buttonList;
    static JButton editButton;
    static JPanel addEdit;
    static JPanel buttonManager;
    static {
        panel = new JPanel(new GridLayout(3,1));
        JTextArea box = new JTextArea(10, 26);
        box.setLineWrap(true);
        panel.add(box, 0); 
        JSeparator line = new JSeparator();
        panel.add(line, 1);
        buttonManager = new JPanel(new GridLayout(1,2));
        buttonList = new JList<>();
        buttonList.setSelectedIndex(0);
        addEdit = new JPanel(new GridLayout(/*2*/1, 1,5,5));
        addButton = new JButton("Add Button");
        editButton = new JButton("Edit/Remove A Button");
        addEdit.add(addButton);
        //addEdit.add(editButton);
        buttonManager.add(buttonList, 0);
        buttonManager.add(addEdit, 1);
        panel.add(buttonManager, 2);
    }
    
    public static void promptNewPage() {
        DefaultListModel<Button> list = new DefaultListModel<Button>();
        buttonList = new JList<Button>(list);
        buttonManager.remove(buttonList);
        buttonList.setVisibleRowCount(12);
        buttonManager.add(buttonList,0);
        addButton.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                try {
                    list.add(0, Popup.promptNewButton());
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