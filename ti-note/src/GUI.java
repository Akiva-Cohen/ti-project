import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.Flow;

public class GUI {

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
        //JPanel fileWindow = new JPanel(new FlowLayout());
        JFileChooser filer = new JFileChooser();
        int selection = filer.showSaveDialog(window);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File file = filer.getSelectedFile();
            createTxt(text, file.getAbsolutePath());
        }
        window.dispose();
    }
    
    public static void createTxt(String text, String name) {
        File output = new File(name + ".txt");
        try {
            FileWriter write = new FileWriter(output);
            write.write(text);
            write.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
    /*public class GUI extends JFrame implements ActionListener {
        JTextField textBox;
        JButton submit;
        public GUI() {
            setSize(500, 500);
        }
    }*/
}
