import java.awt.*;
import java.sql.JDBCType;

import javax.swing.*;
public class Popup extends JOptionPane {
    static final char[] base = {'0', '0'};
    static Object[] options = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "next", "previous"};
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

        showConfirmDialog(null, panel, "select launch mode",JOptionPane.OK_OPTION);
        
            if (main.isSelected()) {
                return true;
            } else if (legacy.isSelected()) {
                return false;
            }
        throw new IllegalAccessError("they exited");
    }
    public static Button promptNewButton() {
        return promptNewButton(0, new Code(base));
    }
    public static Button promptNewButton(Object previousDrop) {
        return promptNewButton(previousDrop, new Code(base));
    }
    public static Button promptNewButton (Code previousCode) {
        return promptNewButton(0,previousCode);
    }
    public static Button promptNewButton(Object previousDrop, Code previousCode) {
        Code outCode;
        Object outDrop;
        outCode = new Code(base);
        buttonDrop.setSelectedItem(previousDrop);
        codeArea1.setText(previousCode.toString());
        int worked = showConfirmDialog(null, buttonPanel, "Enter Button Information", JOptionPane.OK_CANCEL_OPTION);
        if (worked == OK_OPTION) {
            try {
                outCode = new Code(codeArea1.getText().trim().toCharArray());
                outDrop = buttonDrop.getSelectedItem();
            } catch (IllegalArgumentException e) {
                showMessageDialog(null, "Invalid code");
                throw new IllegalAccessError("can you not put bad stuff in");
            }
        } else {
            throw new IllegalAccessError("can you stop abandoning stuff pls");
        }
        Integer one = 1;
        if (outDrop.getClass().equals(one.getClass())) {
            return new NumButton((int)outDrop, outCode);
        } else if (outDrop.equals("next")) {
            return new NextButton(outCode);
        } else if (outDrop.equals("previous")) {
            return new PreviousButton(outCode);
        } else {
            throw new IllegalAccessError("idk how you even pulled this one off");
        }
    }

}