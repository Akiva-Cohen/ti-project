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

            if (previous < 0 && isLoop) {
                if (isLoop) {
                    previous = pages.length - 1;
                    pages[i].setPrevious(Code.intToCode(previous));
                } else {
                    pages[i].removePrevious();
                }
            }
            if (previous >= 0) {
                pages[i].setPrevious(Code.intToCode(previous));
            }
            if (next == pages.length) {
                if (isLoop) {
                pages[i].setNext(Code.intToCode(0));
                } else {
                    pages[i].removeNext();
                }
            }
            if (next != pages.length) {
                pages[i].setNext(Code.intToCode(next));
            }
            out[i] = pages[i].buildPage();
        }
        String thus = "";
        for (String i : out) {
            thus += i + "\n";
        }
        thus += "Lbl θ\nClrHome";
        return thus;
    }
}