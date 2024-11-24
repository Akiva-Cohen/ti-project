public class Line {
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
        if (text.length() > 23) {
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
    //
    public int getButtonNumber() {
        return button.getNumber();
    }
}
