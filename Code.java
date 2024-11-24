public class Code {
    char[] code;
    public static final char[] allowed = {'A','B','C','D','E','F','G','H','I','J', 'k','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9','θ'};
    public Code(char[] code) {
        try {
            if (!checkCode(code)) {
                throw new IllegalAccessException("may only use basic charachters in code");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        this.code = code;
    }

    public char[] getCode() {
        return code;
    }

    public String toString() {
        String out = new String(code);
        return out;
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
}
