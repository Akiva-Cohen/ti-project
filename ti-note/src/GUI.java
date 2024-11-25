import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI {
    public static void stuff() {
        JFrame window = new JFrame("testtest");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField box = new JTextField(20);
        window.add(box);
        JButton submit = new JButton("submit");
        window.add(submit);
        window.pack();
        
    }
    /*public class GUI extends JFrame implements ActionListener {
        JTextField textBox;
        JButton submit;
        public GUI() {
            setSize(500, 500);
        }
    }*/
}
