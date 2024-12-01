import javax.swing.*;
import java.awt.*;
public class App {
    public static void main(String[] args) throws Exception {
        UIManager.put("swing.boldMetal", Boolean.FALSE);  // Disable bold metal rendering
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("sun.java2d.dpiaware", "true");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        System.setProperty("sun.java2d.uiScale", "2.0"); // Set scaling factor



        if (Popup.startUpPopUp()) {
            GUI.standardUI();
        } else {   
            GUI.textNoteWindow();
        }
    }
}
