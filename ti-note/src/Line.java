public class Line {
    public static final int lineLength = 26;
    String text;
    Boolean hasButton;
    Button button;
    public Line(String text) {
        if (text.length() > 26) {
            throw new IllegalArgumentException("String too long");
        }
        this.text = text;
        hasButton = false;
    }

    public Line(String text, int buttonNumber) {
        if (text.length() > 24) {
            throw new IllegalArgumentException("String too long");
        }
        try {
            this.button = new Button(buttonNumber);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
        this.text = text;
        hasButton = true;
    }
    //returns the output to be displayed on that line
    public String toString() {
        String out = "";
        if(hasButton) {
            out = button + text;
        } else {
            out = text;
        }
        return out;
    }
    
    public boolean getHasButton() {
        return hasButton;
    }

    public String dispCode(int lineNumber) {
        String out = "Output(" + lineNumber + ",1,\"";
        if (hasButton) {
            out += button.toString();
        }
        out += text + "\")";
        return out;
    }
    //
    public int getButtonNumber() {
        return button.getNumber();
    }
}
