import java.awt.GridLayout;

import javax.swing.*;
public class Popup extends JOptionPane {
    static final char[] base = {'0', '0'};
    static Object[] options = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "next", "previous", "enter"};
    static JComboBox<Object> buttonDrop = new JComboBox<Object>(options);
    static JTextField codeArea1 = new JTextField(1);
    static JPanel buttonPanel;
    static {
        buttonPanel = new JPanel(new GridLayout(2,2));
        buttonPanel.add(new JLabel("Choose Button"));
        buttonPanel.add(new JLabel("Enter Destination"));
        buttonPanel.add(buttonDrop);
        buttonPanel.add(codeArea1);
    }
    public static void promptNewButton() {
        promptNewButton(0, new Code(base));
    }
    public static void promptNewButton(Object previousDrop) {
        promptNewButton(previousDrop, new Code(base));
    }
    public static void promptNewButton (Code previousCode) {
        promptNewButton(0,previousCode);
    }
    public static void promptNewButton(Object previousDrop, Code previousCode) {
        Code outCode;
        buttonDrop.setSelectedItem(previousDrop);
        codeArea1.setText(previousCode.toString());
        int worked = showConfirmDialog(null, buttonPanel, "Enter Button Information", JOptionPane.OK_CANCEL_OPTION);
        if (worked == OK_OPTION) {
            try {
                outCode = new Code(codeArea1.getText().trim().toCharArray());
            } catch (IllegalArgumentException e) {
                showMessageDialog(null, "Invalid code");
            }
        }
    }

}