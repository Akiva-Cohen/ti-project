import javax.swing.*;
import java.awt.*;
public class App {
    public static void main(String[] args) throws Exception {
        UIManager.put("swing.boldMetal", Boolean.FALSE);  // Disable bold metal rendering
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("sun.java2d.dpiaware", "true");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());


        if (Popup.startUpPopUp()) {
            GUI.standardUI();
        } else {   
            GUI.textNoteWindow();
        }
    }
}
