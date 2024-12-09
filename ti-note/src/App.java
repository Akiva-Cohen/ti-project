    import javax.swing.*;
    public class App {
        public static void main(String[] args) throws Exception {
            UIManager.setLookAndFeel(/*UIManager.getSystemLookAndFeelClassName()*/"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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