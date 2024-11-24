public class Page {
    Line[] lines;
    boolean hasButton;
    public Page(Line[] lines) {
        if (lines.length > 10) {
            throw new IllegalArgumentException("only 10 lines allowed");
        }
        this.lines = lines;
        this.hasButton = checkForButton(lines);
    }

    //builds code for the page
    public String buildPage(Code key, Code next, Code previous) {
        String out = buildPageDisp(key) + buildPageCode(next, previous);
        for (String line : buildCode()) {
            out += line + "\n";
        }
        return out;
    }
    public String buildPageDisp(Code key) {
        String codeUse = "Lbl " + key + "\n";
        String out = codeUse;
        for (String line : buildDisp()) {
            out += line + "\n";
        }
        out += "\n";
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
    //assembles page backend
    public String buildPageCode(Code next, Code previous) {
        String out = "";
        for (String line : buildCode()) {
            out += line + "\n";
        }
        return out;
    }
    //builds backend of page
    public String[] buildCode() {
        String[] arr = {"", ""};
        return arr;
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