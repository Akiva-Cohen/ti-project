public class Line {
    String text;
    Boolean hasButton;
    int buttonNumber;
    Button button;
    public Line(String text) {
        if (text.length() > 26) {
            throw new IllegalArgumentException("String too long");
        }
        this.text = text;
        hasButton = false;
        buttonNumber = -1;
    }

    public Line(String text, int buttonNumber) {
        if (text.length() > 23) {
            throw new IllegalArgumentException("String too long");
        }
        if (buttonNumber > 9 || buttonNumber < 0) {
            throw new IllegalArgumentException("Button must have a positive single digit value");
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
            out = " " + buttonNumber + ":" + text;
        } else {
            out = text;
        }
        return out;
    }
    //
    public int getButtonNumber() {
        return buttonNumber;
    }
}
