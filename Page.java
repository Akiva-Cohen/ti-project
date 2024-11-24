public class Page {
    static char[] allowed = {'A','B','C','D','E','F','G','H','I','J',
    'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
    '0','1','2','3','4','5','6','7','8','9','θ'};
    Line[] lines;
    char[] code;
    boolean hasButton;
    public Page(Line[] lines, char[] code) {
        if (lines.length > 10) {
            throw new IllegalArgumentException("only 10 lines allowed");
        }
        try {
            if (!checkCode(code)) {
                throw new IllegalAccessException("may only use basic charachters in code");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        this.lines = lines;
        this.code = code;
        this.hasButton = checkForButton(lines);
    }
    
    //builds code for the page
    public String buildPage() {
        String codeString = new String(code);
        String codeUse = "Lbl " + codeString + "\n";
        String out = codeUse;
        for (String line : buildDisp()) {
            out += line + "\n";
        }
        out += "\n";
        for (String line : buildCode()) {
            out += line + "\n";
        }
        return out;
    }
    //builds text for the page
    public String[] buildDisp() {
        String[] out = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            out[i] = lines[i].dispCode(i+1);
        }
        return out;
    }
    //builds backend of page
    public String[] buildCode() {
        String[] arr = {"", ""};
        return arr;
    }
    //returns true if code is an acceptable code
    public static boolean checkCode(char[] code) {
        if (code.length != 2) {
            throw new IllegalArgumentException("Codes Must be 2 charachters");
        }
        if (checkChar(code[0]) && checkChar(code[1])) {
            return true;
        }
        return false;
    }
    //returns true if char can be used in codes
    public static boolean checkChar(char letter) {
        for (char check : allowed) {
            if (check == letter) {
                return true;
            }
        }
        return false;
    }
    //checks if any of the lines have buttons
    public static boolean checkForButton(Line[] lines) {
        for (Line a : lines) {
            if (a.getHasButton()) {
                return true;
            }
        }
        return false;
    }
}
