import java.util.Arrays;

public class Code {
    char[] code;
    public static final char[] allowed = {'A','B','C','D','E','F','G','H','I','J', 'k','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
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

    public boolean equals(Code obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (getCode().equals(obj.getCode())){
            return true;
        }
        return false;
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
    public static Code intToCode(int seed) {
        if (seed > (allowed.length * allowed.length -1) || seed < 0) {
            throw new IllegalArgumentException("invalid seed");
        }
        int num1 = seed / allowed.length;
        int num2 = seed % allowed.length;
        char c1 = allowed[num1];
        char c2 = allowed[num2];
        char[] arr = {c1, c2};
        Code out = new Code(arr);
        return out;
    }

}
