import java.awt.GridLayout;

import javax.swing.*;
public class PageMaker extends JOptionPane {
    static JPanel panel;
    static char[] chars = {'0', '0'};
    static Button[] list = {new NumButton(1, new Code(chars)), new NumButton(1, new Code(chars))};
    static {
        panel = new JPanel(new GridLayout(2,1));
        JTextArea box = new JTextArea(10, 26);
        box.setLineWrap(true);
        panel.add(box);
        JPanel buttonManager = new JPanel(new GridLayout(1,2));
        JList<Button> buttonList = new JList<>(list);
        buttonList.setSelectedIndex(0);
        JButton addButton = new JButton("Add Button");
        buttonManager.add(buttonList);
        buttonManager.add(addButton);
        panel.add(buttonManager);
    }
    public static void promptNewPage() {
        showConfirmDialog(null, panel,"Make New Button", JOptionPane.OK_CANCEL_OPTION);
    }
}