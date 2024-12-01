import javax.swing.*;
import java.awt.*;
public class App {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        try {
            boolean op = Popup.startUpPopUp();
            if (op) {
                GUI.standardUI();
            } else if (!op) {
                GUI.textNoteWindow();
            }
        } catch (IllegalAccessError e) {}
    }
}

