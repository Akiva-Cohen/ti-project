import java.util.*;
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
        String out = buildPageDisp(key) + buildPageCode(key, next, previous);
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
    public String buildPageCode(Code key, Code next, Code previous) {
        String out = "";
        for (String line : codeBuilder(key, next, previous)) {
            out += line + "\n";
        }
        return out;
    }
    //extra stuff after buildCode()
    public ArrayList<String> codeBuilder(Code key, Code next, Code previous) {
        ArrayList<String> out = buildButtonListener();
        out.addAll(buildFlipCode(key, next, previous));
        out.addAll(buttonBuilder());
        out.add("Goto " + key);
        return out;
    }
    //builds backend of page
    public ArrayList<String> buildFlipCode(Code key, Code next, Code previous) {
        if (key.equals(next) || key.equals(previous)) {
            if (key.equals(next) && key.equals(previous)) {
                return new ArrayList<String>();
            } else if (key.equals(next)) {
                return buildFlipCode(previous);
            } else {
                return buildFlipCode(next, 0);
            }
        } else {
            return buildFlipCode(next, previous);
        }
    }
    public ArrayList<String> buildButtonListener() {
        ArrayList<String> out = new ArrayList<String>();
        out.add("Repeat K≠0");
        out.add("getKey→K");
        out.add("end");
        return out;
    }
    public ArrayList<String> buildFlipCode(Code next, int fill) {
        ArrayList<String> out = new ArrayList<String>();
        out.add(Button.nextButton(next));
        return out;
    }
    public ArrayList<String> buildFlipCode(Code previous) {
        ArrayList<String> out = new ArrayList<String>();
        out.add(Button.previousButton(previous));
        return out;
    }
    public ArrayList<String> buildFlipCode(Code next, Code previous) {
        ArrayList<String> out = new ArrayList<String>();
        out.add(Button.nextButton(next));
        out.add(Button.previousButton(previous));
        return out;
    }
    //adds all number buttons
    public ArrayList<String> buttonBuilder(){
        ArrayList<String> out = new ArrayList<String>();
        return out;
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