public class NextButton extends Button {

    public static final int nextCode = 26;

    public NextButton(Code destination) {
        super(destination);
    }
    public String toString() {
        return "next:" + destination;
    }

    public String makeButtonCode() {
        return codeButton(nextCode, destination);
    }
}
