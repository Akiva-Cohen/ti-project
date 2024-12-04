
public class Code {
    char[] code;
    public static final char[] allowed = {'A','B','C','D','E','F','G','H','I','J', 'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9', 'θ'};
    public Code(Integer[] nums) {
        this(intArrToCharsArr(nums));
    }
    public static char[] intArrToCharsArr(Integer[] nums) {
        char char1 = allowed[nums[0]];
        char char2 = allowed[nums[1] * 2];
        char[] out = {char1, char2};
        return out;
    }
    public Code(char[] code) {
        try {
            if (!checkCode(code)) {
                throw new IllegalArgumentException("may only use basic charachters in code");
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

    public static Integer[] charrArrToIntArr(char[] chars) {
        Integer[] out = {charToInt(chars[0]), charToInt(chars[1])};
        return out;
    }

    public boolean equals(Code obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (new String(getCode()).equals(new String(obj.getCode()))){
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
        if (seed > (allowed.length * (allowed.length / 2) -1) || seed < 0) {
            throw new IllegalArgumentException("invalid seed");
        }
        int num1 = seed / (allowed.length / 2);
        int num2 = seed % (allowed.length / 2);
        char c1 = allowed[num1];
        char c2 = allowed[num2 * 2];
        char[] arr = {c1, c2};
        Code out = new Code(arr);
        return out;
    }
    public static Integer charToInt(char in) {
        for (int i = 0; i < allowed.length; i++) {
            if (in == allowed[i]) {
                return i;
            }
        }
        return -1;
    }
    public String destString() {
        char[] chars = getCode();
        String out = charToInt(chars[0]).toString() + "-" + (charToInt(chars[1]) / 2);
        return out;
    }
    public Code getNextCode() {
        char[] news = {code[0], allowed[charToInt(code[1]) + 1]};
        return new Code(news);
    }
}
