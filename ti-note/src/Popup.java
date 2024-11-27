import java.awt.*;
import java.sql.JDBCType;

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
    //returns true to go to main version, false to go to legacy mode
    public static boolean startUpPopUp() {
        JPanel panel = new JPanel(new FlowLayout());
        
        JRadioButton main = new JRadioButton("Use Page Manager Mode");
        JRadioButton legacy = new JRadioButton("Use Single Text / Legacy mode (V0.1.0)");
        ButtonGroup group = new ButtonGroup();
        group.add(main);
        group.add(legacy);
        main.setSelected(true);
        
        panel.add(main);
        panel.add(legacy);

        showMessageDialog(null, panel, "select launch mode",JOptionPane.DEFAULT_OPTION);
        
            if (main.isSelected()) {
                return true;
            } else if (legacy.isSelected()) {
                return false;
            }
        return false;
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