public class FlipBook extends Program {
    public FlipBook(Page[] pages) {
        super(pages);
    }
    public String programBuild(boolean isLoop) {
        String[] out = new String[getPages().length];
        for (int i = 0; i < getPages().length; i++) {
            int previous = i - 1;
            if (previous < 0) {
                if (isLoop) {
                    previous = getPages().length - 1;
                } else {
                    previous = 0;
                }
            }
            int next = i + 1;
            if (next == getPages().length) {
                if (isLoop) {
                    next = 0;
                } else {
                    next--;
                }
            }
            out[i] = getPages()[i].buildPage(Code.intToCode(i), Code.intToCode(next), Code.intToCode(previous));
        }
        String thus = "";
        for (String i : out) {
            thus += i + "\n";
        }
        return thus;
    }
}