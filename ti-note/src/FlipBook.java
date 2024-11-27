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
                previous = pages.length - 1;
                pages[i].addButton(new PreviousButton(Code.intToCode(previous)));
            }
            if (previous >= 0) {
                pages[i].addButton(new PreviousButton(Code.intToCode(previous)));
            }
            if (next == pages.length) {
                pages[i].addButton(new NextButton(Code.intToCode(0)));
            }
            if (next != pages.length) {
                pages[i].addButton(new NextButton(Code.intToCode(next)));
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