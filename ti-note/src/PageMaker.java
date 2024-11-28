import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class PageMaker extends JOptionPane {
    static final char[] chars = {'0', '0'};
    static final Code basic = new Code(chars);
    static JPanel panel;
    static JButton addButton;
    static JList<Button> buttonList;
    static JButton editButton;
    static JPanel addEdit;
    static JPanel buttonManager;
    static Object[] options = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "next", "previous"};
    static JComboBox<Object> buttonDrop = new JComboBox<Object>(options);
    static JTextField codeArea1 = new JTextField(1);
    static JButton apply = new JButton("Apply");
    static JButton delete = new JButton("Delete");
    static JPanel buttonPanel;
    static JTextArea box = new JTextArea(10, 26);
    static JTextField keyField = new JTextField();
    static {
        buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.add(buttonDrop);
        buttonPanel.add(codeArea1);
        buttonPanel.add(apply);
        buttonPanel.add(delete);
        panel = new JPanel(new GridLayout(3,1));
        box.setLineWrap(true);
        box.setColumns(26);
        panel.add(box, 0); 
        JSeparator line = new JSeparator();
        JPanel middle = new JPanel(new GridLayout(3,1));
        middle.add(line);
        middle.add(keyField);
        panel.add(middle,1);
        buttonManager = new JPanel(new GridLayout(1,3));
        buttonList = new JList<>();
        buttonList.setSelectedIndex(0);
        addEdit = new JPanel(new GridLayout(/*2*/1, 1,5,5));
        addButton = new JButton("Add Button");
        addEdit.add(addButton);
        buttonManager.add(buttonList, 0);
        buttonManager.add(buttonPanel,1);
        buttonManager.add(addEdit, 2);
        panel.add(buttonManager, 2);
    }
    
    public static Page promptNewPage() {
        buttonPanel.setVisible(false);
        DefaultListModel<Button> list = new DefaultListModel<Button>();
        buttonList = new JList<Button>(list);
        buttonManager.remove(buttonList);
        buttonList.setVisibleRowCount(12);
        buttonManager.add(buttonList,0);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                try {
                    list.add(0, Popup.promptNewButton());
                } catch (IllegalAccessError ee) {}
            }
        });
        buttonList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (buttonList.getSelectedValue() == null) {
                    buttonPanel.setVisible(false);
                } else {
                    codeArea1.setText(buttonList.getSelectedValue().getDestination().toString());
                    if(buttonList.getSelectedValue().getClass().equals(new NextButton(basic).getClass())) {
                        buttonDrop.setSelectedItem("next");
                    } else if (buttonList.getSelectedValue().getClass().equals(new PreviousButton(basic).getClass())) {
                        buttonDrop.setSelectedItem("previous");
                    } else {
                        buttonDrop.setSelectedItem(buttonList.getSelectedValue().getNumber());
                    }
                    buttonPanel.setVisible(true);
                }
                
            }
        });
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.remove(buttonList.getSelectedIndex());
            }
        });
        apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Code outCode;
                Button out;
                try {
                    outCode = new Code(codeArea1.getText().trim().toCharArray());
                } catch (IllegalArgumentException ee) {
                    showMessageDialog(null, "Invalid code");
                    throw new IllegalAccessError("can you not put bad stuff in");
                }
                if (buttonDrop.getSelectedItem().equals("next")) {
                    out = new NextButton(outCode);
                } else if (buttonDrop.getSelectedItem().equals("previous")) {
                    out = new PreviousButton(outCode);
                } else {
                    out = new NumButton((int)buttonDrop.getSelectedItem(), outCode);
                }
                list.set(buttonList.getSelectedIndex(), out);
            }
        });
        box.setColumns(26);
        showConfirmDialog(null, panel,"Make New Page", JOptionPane.OK_CANCEL_OPTION);
        Object[] arrOut = list.toArray();
        Button[] buttonsOut = new Button[arrOut.length];
        for (int i = 0; i < arrOut.length; i++) {
            buttonsOut[i] = (Button)arrOut[i];
        }
        boolean hasNext = false;
        boolean hasPrevious = false;
        Button genPrevious = new PreviousButton(basic);
        Button genNext = new NextButton(basic);
        ArrayList<Integer> buttonNums = new ArrayList<>();
        for (int i = 0; i < arrOut.length; i++) {
            if (buttonsOut[i].getClass().equals(genPrevious.getClass())) {
                if (hasPrevious) {
                    showMessageDialog(null, "Can only have one previous button");
                    throw new IllegalAccessError("multiple previous buttons");
                } else {
                    hasPrevious = true;
                }
            } else if (buttonsOut[i].getClass().equals(genNext.getClass())) {
                if (hasNext) {
                    showMessageDialog(null, "Can only have one next button");
                    throw new IllegalAccessError("multiple next buttons");
                } else {
                    hasNext = true;
                }
            } else {
                if (buttonNums.contains(buttonsOut[i].getNumber())) {
                    showMessageDialog(null, "can only have one of each button number");
                    throw new IllegalAccessError("multiple of the same number button");
                } else {
                    buttonNums.add(buttonsOut[i].getNumber());
                }
            }
        }
        Code outCode;
        try {
            outCode = new Code(keyField.getText().toCharArray());
        } catch (IllegalArgumentException e) {
            showMessageDialog(null, "use valid code pls");
            throw new IllegalAccessError("Used invalid code");
        }
        Page out;
        try {
            out = new Page(box.getText(), outCode, buttonsOut);
        } catch (IllegalArgumentException e) {
            showMessageDialog(null, "text too long");
            throw new IllegalAccessError("text too long");
        }
        return out;
    }
    public static Button[] buttonListToArray(ArrayList<Button> list) {
        Button[] out = new Button[list.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = list.get(i);
        }
        return out;
    }
}