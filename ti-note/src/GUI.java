import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.Flow;
public class GUI {
    public static String path = "C:\\Users\\Minio\\OneDrive\\Desktop\\ti-project\\";
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
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code =TextNote.submitTextNote(box.getText(), loop.isSelected());
                createTxt(code, "test");
            }
        });
    }
    
    public static void createTxt(String text, String name) {
        File output = new File(path + name + ".txt");
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
