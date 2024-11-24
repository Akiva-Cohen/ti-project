public class Page {
    static char[] allowed = {'A','B','C','D','E','F','G','H','I','J',
    'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
    '0','1','2','3','4','5','6','7','8','9','θ'};
    Line[] lines;
    char[] code;
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
    }
    //returns true if code is an acceptable code
    public Boolean checkCode(char[] code) {
        if (code.length != 2) {
            throw new IllegalArgumentException("Codes Must be 2 charachters");
        }
        if (checkChar(code[0]) && checkChar(code[1])) {
            return true;
        }
        return false;
    }
    //returns true if char can be used in codes
    public Boolean checkChar(char letter) {
        for (char check : allowed) {
            if (check == letter) {
                return true;
            }
        }
        return false;
    }
}
