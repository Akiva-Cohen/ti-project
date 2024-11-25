import java.util.HashMap;
public class Button {
    int buttonNumber;
    public static final int nextCode = 26;
    public static final int backCode = 24;

    public static final HashMap<Integer, Integer> keyMap;
    static {
        keyMap = new HashMap<>(10);
        keyMap.put(0,102);
        keyMap.put(1,92);
        keyMap.put(2,93);
        keyMap.put(3, 94);
        keyMap.put(4,82);
        keyMap.put(5,83);
        keyMap.put(6,84);
        keyMap.put(7,72);
        keyMap.put(8,73);
        keyMap.put(9,74);
    }
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
    public static int numToCode(int buttonNum) {
        return keyMap.get(buttonNum);
    }
    public static String nextButton(Code destination) {
        return codeButton(Button.nextCode, destination);
    }
    public static String previousButton(Code destination) {
        return codeButton(Button.backCode, destination);
    }
    public static String numButton(int num, Code destination) {
        return codeButton(numToCode(num), destination);
    }
    public static String codeButton(int buttonCode, Code destination) {
        String out = "If K=" + buttonCode + ":Goto " + destination;
        return out;
    }
}
