import javax.swing.*;
public class PageMaker extends JPanel {
    public PageMaker() {
        super();
        JTextArea box = new JTextArea(10, 26);
        box.setLineWrap(true);
        add(box);
        
    }
}