import java.util.ArrayList;
public class FlipBook extends Program {
    public FlipBook(Page[] pages) {
        super(pages);
    }
    public String programBuild(boolean isLoop) {
        Page[] pages = getPages();
        String[] out = new String[pages.length];
        for (int i = 0; i < pages.length; i++) {
            int previous = i - 1;
            int next = i + 1;
            pages[i].setPrevious(Code.intToCode(previous));
            pages[i].setNext(Code.intToCode(next));

            if (previous < 0 && isLoop) {
                if (isLoop) {
                    previous = pages.length - 1;
                    pages[i].setPrevious(Code.intToCode(previous));
                } else {
                    pages[i].removePrevious();
                }
            }
            if (next == getPages().length) {
                if (isLoop) {
                pages[i].setNext(Code.intToCode(0));
                } else {
                    pages[i].removeNext();
                }
            }
            out[i] = pages[i].buildPage();
        }
        String thus = "";
        for (String i : out) {
            thus += i + "\n";
        }
        return thus;
    }
}