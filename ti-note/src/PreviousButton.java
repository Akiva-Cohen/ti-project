public class PreviousButton extends Button {

    public static final int backCode = 24;


    public PreviousButton(Code destination) {
        super(destination);
    }

    public String makeButtonCode() {
        return codeButton(backCode, destination);
    }
    public String toString() {
        return "previous:" + destination.destString();
    }
}
