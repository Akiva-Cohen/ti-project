public class Button {
    Code destination;
    public Button(Code destination) {
        this.destination = destination;
    }
    public int getNumber() {
        throw new IllegalAccessError("why on gods green earth would you call this");
    }
    public Code getDestination() {
        return destination;
    }
    public String makeButtonCode() {
        throw new IllegalAccessError("make a subclass pls");
    }

    public void setDestination(Code in) {
        destination = in;
    }


    public static String codeButton(int buttonCode, Code destination) {
        String out = "If K=" + buttonCode + ":Goto " + destination;
        return out;
    }
}
