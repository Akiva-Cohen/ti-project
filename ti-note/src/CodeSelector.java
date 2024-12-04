import java.util.stream.IntStream;
import javax.swing.*;
import java.awt.*;
public class CodeSelector extends JPanel {
    static Integer[] basicArr = {0,0};
    JComboBox<Integer> codeNums1;
    JComboBox<Integer> codeNums2;
    public CodeSelector() {
        this(basicArr);
    }
    public CodeSelector(String key) {
        this(new Code(key.toCharArray()));
    }
    public CodeSelector(Code key) {
        this(codeToInts(key));
    }
    public CodeSelector(Integer[] prev) {
        super(new GridLayout(1,2));
        int[] codeNums01 = IntStream.rangeClosed(0, 35).toArray();
        Integer[] codeNums001 = new Integer[codeNums01.length];
        for (int i = 0; i < codeNums01.length; i++) {
            codeNums001[i] = codeNums01[i];
        }
        codeNums1 = new JComboBox<Integer>(codeNums001);
        codeNums1.setSelectedItem(prev[0]);
        int[] codeNums02 = IntStream.rangeClosed(0, 17).toArray();
        Integer[] codeNums002  = new Integer[codeNums02.length];
        for (int i = 0; i < codeNums02.length; i++) {
            codeNums002[i] = codeNums02[i];
        }
        codeNums2 = new JComboBox<Integer>(codeNums002);
        codeNums2.setSelectedItem(prev[1]);
        add(codeNums1);
        add(codeNums2);
    }
    public void setCode(Code prev) {
        char[] chars = prev.getCode();
        codeNums1.setSelectedItem(Code.charToInt(chars[0]));
        codeNums2.setSelectedItem(Code.charToInt(chars[1]) / 2);
    }
    public static Integer[] codeToInts(Code key) {
        char[] chars = key.getCode();
        int num1 = Code.charToInt(chars[0]);
        int num2 = Code.charToInt(chars[1]) / 2;
        Integer[] out = {num1, num2};
        return out;
    }
    public Code getSelection() {
        Integer num1 = (Integer) codeNums1.getSelectedItem();
        Integer num2 = (Integer) codeNums2.getSelectedItem();
        Integer[] numsArr = {num1, num2};
        return new Code(numsArr);
    }
}
