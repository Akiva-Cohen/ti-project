public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        //PageMaker.promptNewPage();
        if (Popup.startUpPopUp()) {
            Page cool = PageMaker.promptNewPage();
            PageMaker.promptNewPage();
        } else {
            GUI.textNoteWindow();
        }
    }
}
