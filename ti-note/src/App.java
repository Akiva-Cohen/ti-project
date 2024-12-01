import javax.swing.*;
import java.awt.*;
public class App {
    public static void main(String[] args) throws Exception {

        if (Popup.startUpPopUp()) {
            GUI.standardUI();
        } else {
            GUI.textNoteWindow();
        }
    }
}
