public class Button {
    int buttonNumber;

    public Button(int buttonNumber) {
        if (buttonNumber > 9 || buttonNumber < 0) {
            throw new IllegalArgumentException("Button must have a positive single digit value");
        }
        this.buttonNumber = buttonNumber;
    }
    public int getNumber() {
        return buttonNumber;
    }

    public String toString() {
        return buttonNumber + ":";
    }
}
