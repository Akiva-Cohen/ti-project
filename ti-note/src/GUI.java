import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI {
    public static void standardUI() {
        StandardFrame frame = new StandardFrame();
        frame.setSize(800, 500);
        JPanel panel = new JPanel(new GridLayout(1, 3));
        DefaultListModel<Page> list = new DefaultListModel<>();
        JList<Page> pageList = new JList<Page>(list);
        panel.add(pageList);
        JPanel editPanel = new JPanel(new GridLayout(2, 1));
        JButton up = new JButton("Make First");
        editPanel.add(up);
        JButton edit = new JButton("Edit");
        editPanel.add(edit);
        panel.add(editPanel);
        editPanel.setVisible(false );
        JButton add = new JButton("Add Page");
        panel.add(add);
        frame.add(panel);
        JButton submit = new JButton("Submit");
        frame.add(submit);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    list.addElement(PageMaker.promptNewPage());
                } catch (IllegalAccessError ee) {}
            }
        });
        pageList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (pageList.getSelectedValue() == null) {
                    editPanel.setVisible(false);
                } else {
                    editPanel.setVisible(true);
                }
            }
        });
        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.add(0,pageList.getSelectedValue());
                list.remove(pageList.getSelectedIndex());
            }
        });
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                list.set(pageList.getSelectedIndex(), PageMaker.update(pageList.getSelectedValue()));
                } catch (IllegalAccessError ee) {

                }
            }
        });
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] objs = list.toArray();
                Page[] pages = new Page[objs.length];
                for (int i = 0; i < objs.length; i++) {
                    pages[i] = (Page)objs[i];
                }
                Program program = new Program(pages);
                if (program.checkConections()) {
                    createTxt(program.programBuild(), frame);
                } else {
                    JOptionPane.showMessageDialog(null, "You Have Buttons Leading to Places that Don't Exist");
                }
            }
        });
    }
    public static void textNoteWindow() {
        JFrame window = new JFrame("testtest");
        JPanel window1 = new JPanel(new FlowLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea box = new JTextArea("enter notes here",30,26);
        window1.add(box);
        JCheckBox loop = new JCheckBox("loops");
        window1.add(loop);
        JButton submit = new JButton("submit");
        window1.add(submit);
        window.add(window1);
        window.pack();
        window.setVisible(true);
        submit.addActionListener(e -> submited(box.getText(), loop.isSelected(), window));
    }
    public static void submited(String text, boolean isLoop, JFrame window) {
        TextNote note = new TextNote(text);
        text = note.programBuild(isLoop);
        //need to find a way to make this work on cheerpj
        createTxt(text, window);
    }
    public static void createTxt(String text, JFrame window) {
        try {
            cheerpD(text);
            window.dispose();
        } catch (UnsatisfiedLinkError e) {
            JFileChooser filer = new JFileChooser();
            int selection = filer.showSaveDialog(window);
            if (selection == JFileChooser.APPROVE_OPTION) {
                File spot = filer.getSelectedFile();
                File output = new File(spot.getAbsolutePath() + ".txt");
                try {
                    FileWriter write = new FileWriter(output);
                    write.write(text);
                    write.close();
                    window.dispose();
                } catch (IOException er) {}
            }
        }
    }
    //adds native download
    public static native void cheerpD(String text);
    
}