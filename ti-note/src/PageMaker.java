import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class PageMaker extends JOptionPane {
    static final char[] chars = {'0', '0'};
    static final Code basic = new Code(chars);
    static JList<Button> buttonList;
    public static Page promptNewPage() {
        return promptNewPage("", new DefaultListModel<Button>(), "");
    }
    public static Page promptNewPage(String startText, DefaultListModel<Button> startList, String key) {
        JTextField keyField = new JTextField(key);
        JTextArea box = new JTextArea(startText,10, 26);
        JPanel buttonPanel;
        JButton delete = new JButton("Delete");
        JButton apply = new JButton("Apply");
        JTextField codeArea1 = new JTextField(1);
        Object[] options = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "next", "previous"};
        JComboBox<Object> buttonDrop = new JComboBox<Object>(options);
        JPanel buttonManager;
        JPanel addEdit;
        JButton addButton;
        JPanel panel;
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
        buttonPanel.setVisible(false);
        DefaultListModel<Button> list = startList;
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
        int ok = showConfirmDialog(null, panel,"Make New Page", JOptionPane.OK_CANCEL_OPTION);
        if (ok == YES_OPTION) {
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
            boolean redo = false;
            for (int i = 0; i < arrOut.length; i++) {
                if (buttonsOut[i].getClass().equals(genPrevious.getClass())) {
                    if (hasPrevious) {
                        redo = true;
                    } else {
                        hasPrevious = true;
                    }
                } else if (buttonsOut[i].getClass().equals(genNext.getClass())) {
                    if (hasNext) {
                        redo = true;
                    } else {
                        hasNext = true;
                    }
                } else {
                    if (buttonNums.contains(buttonsOut[i].getNumber())) {
                        redo = true;
                    } else {
                        buttonNums.add(buttonsOut[i].getNumber());
                    }
                }
            }
            if (redo) {
                showMessageDialog(null, "You made the same button do multiple things");
                return promptNewPage(box.getText(), list, codeArea1.getText());
            }
            Code outCode;
            try {
                outCode = new Code(keyField.getText().toCharArray());
            } catch (IllegalArgumentException e) {
                showMessageDialog(null, "use valid code pls");
                return promptNewPage(box.getText(), list, codeArea1.getText());
            }
            Page out;
            try {
                out = new Page(box.getText(), outCode, buttonsOut);
            } catch (IllegalArgumentException e) {
                showMessageDialog(null, "text too long");
                return promptNewPage(box.getText(), list, codeArea1.getText());
            }
            return out;
        }
        throw new IllegalAccessError("They cancled");
    }
    public static Button[] buttonListToArray(ArrayList<Button> list) {
        Button[] out = new Button[list.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = list.get(i);
        }
        return out;
    }
}