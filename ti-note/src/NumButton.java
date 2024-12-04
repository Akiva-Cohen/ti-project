import java.util.HashMap;

public class NumButton extends Button {
    int buttonNumber;

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

    
    public NumButton(int buttonNumber, Code destination) {
        super(destination);
        if (buttonNumber > 9 || buttonNumber < 0) {
            throw new IllegalArgumentException("Button must have a positive single digit value");
        }
        this.buttonNumber = buttonNumber;
    }

    public String toString() {
        return "0" + buttonNumber + ":" + destination.destString();
    }
    public int getNumber() {
        return buttonNumber;
    }
    public void setNumber(int in) {
                if (in > 9 || in < 0) {
            throw new IllegalArgumentException("Button must have a positive single digit value");
        }
        buttonNumber = in;
    }

    public String makeButtonCode() {
        return codeButton(numToCode(buttonNumber), destination);
    }

    public static int numToCode(int buttonNum) {
        return keyMap.get(buttonNum);
    }

    
}
