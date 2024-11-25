public class Line {
    public static final int lineLength = 26;
    String text;
    public Line(String text) {
        if (text.length() > 26) {
            throw new IllegalArgumentException("String too long");
        }
        this.text = text;
    }

    //returns the output to be displayed on that line
    public String toString() {
        return text;
    }
    public String getText() {
        return text;
    }

    public String dispCode(int lineNumber) {
        String out = "Output(" + lineNumber + ",1,\"" + text + "\"";
        return out;
    }
}
