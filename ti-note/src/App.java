public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        //PageMaker.promptNewPage();
        if (Popup.startUpPopUp()) {
            GUI.standardUI();
        } else {
            GUI.textNoteWindow();
        }
    }
}
