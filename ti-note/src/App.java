import javax.swing.*;
import java.awt.*;
public class App {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        try {
            if (Popup.startUpPopUp()) {
                GUI.standardUI();
            } else {   
                GUI.textNoteWindow();
            }
        } catch (IllegalAccessError e) {}
    }
}
