import java.util.*;
public class Page {
    Line[] lines;
    ArrayList<Button> buttons;
    Code key;
    public Page(String text, Code key) {
        this(TextNote.textToLinesClassArray(text), key);
    }
    public Page(String text, Code key, Button[] buttons) {
        this(text, key);
        for (Button i : buttons) {
            this.buttons.add(i);
        }
    }
    public Page(Line[] lines, Code key) {
        if (lines.length > 10) {
            throw new IllegalArgumentException("only 10 lines allowed");
        }
        this.lines = lines;
        this.buttons = new ArrayList<Button>();
        this.key = key;
    }

    public void setKey(Code key) {
        if (isDestination(key)) {
            throw new IllegalArgumentException("would make next or previous lead to self");
        } else {
            this.key = key;
        }
    }
    public Code getKey() {
        return key;
    }
    public String toString() {
        return key.destString();
    }
    public String getText() {
        String out = "";
        for (Line line : lines) {
            out += line + "\n";
        }
        return out;
    }
    public ArrayList<Button> getButtons() {
        return buttons;
    }

    //checks if a code is a destination of a button
    public boolean isDestination(Code key) {
       for (Button i : buttons) {
            if (key.equals(i.getDestination())) {
                return true;
            }
        }
        return false;
    }

    public void addButton(Button button) {
        buttons.add(button);
    }
    public void addNumButton(int num, Code destination) {
        addButton(new NumButton(num, destination));
    }
    //builds code for the page
    public String buildPage() {
        String out = buildPageDisp() + buildPageCode();
        return out;
    }
    public String buildPageDisp() {
        String codeUse = "Lbl " + key + "\n";
        String out = codeUse;
        out += "ClrHome\n";
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
    public String buildPageCode() {
        String out = "";
        for (String line : codeBuilder()) {
            out += line + "\n";
        }
        return out;
    }
    //extra stuff after buildCode()
    public ArrayList<String> codeBuilder() {
        ArrayList<String> out = buildButtonListener();
        out.addAll(buttonBuilder());
        out.add("If K=45 or K=22:Goto θ");
        out.add("Goto " + key);
        return out;
    }
    //builds backend of page

    public ArrayList<String> buildButtonListener() {
        ArrayList<String> out = new ArrayList<String>();
        out.add("Repeat K≠0");
        out.add("getKey→K");
        out.add("End");
        return out;
    }
    //adds all buttons
    public ArrayList<String> buttonBuilder(){
        ArrayList<String> out = new ArrayList<String>();
        for (Button i : buttons) {
            out.add(i.makeButtonCode());
        }
        return out;
    }
}