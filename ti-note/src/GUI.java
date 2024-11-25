import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI {
    public static void textNoteWindow() {
        JFrame window = new JFrame("testtest");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea box = new JTextArea("enter notes here",30,26);
        window.add(box);
        JButton submit = new JButton("submit");
        window.add(submit);
        window.setLayout(new FlowLayout());
        window.pack();
        window.setVisible(true);
        submit.addActionListener(e -> TextNote.submitTextNote(box.getText()));
    }


    /*public class GUI extends JFrame implements ActionListener {
        JTextField textBox;
        JButton submit;
        public GUI() {
            setSize(500, 500);
        }
    }*/
}
