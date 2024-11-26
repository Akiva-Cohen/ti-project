import java.awt.GridLayout;

import javax.swing.*;
public class Popup extends JOptionPane {
    static final char[] base = {'0', '0'};
    static Object[] options = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "next", "previous", "enter"};
    static JComboBox<Object> drop = new JComboBox<Object>(options);
    static JTextField codeArea1 = new JTextField(1);
    static JPanel panel;
    static {
        panel = new JPanel(new GridLayout(2,2));
        panel.add(new JLabel("Choose Button"));
        panel.add(new JLabel("Enter Destination"));
        panel.add(drop);
        panel.add(codeArea1);
    }
    static void promptNewButton() {
        promptNewButton(0, new Code(base));
    }
    static void promptNewButton(Object previousDrop) {
        promptNewButton(previousDrop, new Code(base));
    }
    static void promptNewButton (Code previousCode) {
        promptNewButton(0,previousCode);
    }
    static void promptNewButton(Object previousDrop, Code previousCode) {
        Code outCode;
        drop.setSelectedItem(previousDrop);
        codeArea1.setText(previousCode.toString());
        int worked = showConfirmDialog(null, panel, "Enter Button Information", JOptionPane.OK_CANCEL_OPTION);
        if (worked == OK_OPTION) {
            try {
                outCode = new Code(codeArea1.getText().trim().toCharArray());
            } catch (IllegalArgumentException e) {
                showMessageDialog(null, "Invalid code");
            }
        }
    }

}