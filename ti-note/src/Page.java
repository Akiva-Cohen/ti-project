import java.util.*;
public class Page {
    Line[] lines;
    ArrayList<Button> buttons;
    Code key;
    boolean hasNext;
    Code next;
    boolean hasPrevious;
    Code previous;
    public Page(Line[] lines, Code key) {
        if (lines.length > 10) {
            throw new IllegalArgumentException("only 10 lines allowed");
        }
        this.lines = lines;
        this.buttons = new ArrayList<Button>();
        hasNext = false;
        hasPrevious = false;
    }
    public Page(Line[] lines, Code key, Code next, Code previous) {
        this(lines, key);
        hasNext = true;
        hasPrevious = true;
        this.next = next;
        this.previous = previous;
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

    //checks if a code is a destination of a button
    public boolean isDestination(Code key) {
        if (hasNext) {
            if (key.equals(next)) {
                return true;
            }
        }
        if (hasPrevious) {
            if (key.equals(previous)) {
                return true;
            }
        }
        for (Button i : buttons) {
            if (key.equals(i.getDestination())) {
                return true;
            }
        }
        return false;
    }
    public void setNext(Code next) {
        if (next.equals(key)) {
            throw new IllegalArgumentException("buttons cannot lead to self");
        } else {
            this.next = next;
            hasNext = true;
        }
    }
    public boolean hasNext() {
        return hasNext;
    }
    public Code getNext() {
        if (!hasNext) {
            throw new IllegalAccessError("next does not exist");
        } else {
            return next;
        }
    }
    public void removeNext() {
        hasNext = false;
    }

    public void setPrevious(Code previous) {
        if (previous.equals(key)) {
            throw new IllegalArgumentException("buttons cannot lead to self");
        } else {
            this.previous = previous;
            hasPrevious = true;
        }
    }
    public boolean hasPrevious() {
        return hasPrevious;
    }
    public Code getPrevious() {
        if (!hasPrevious) {
            throw new IllegalAccessError("previous does not exist");
        } else {
            return previous;
        }
    }
    public void removePrevious() {
        hasPrevious = false;
    }

    public void addButton(Button button) {
        buttons.add(button);
    }
    public void addButton(int num, Code destination) {
        addButton(new Button(num, destination));
    }
    //builds code for the page
    public String buildPage() {
        String out = buildPageDisp() + buildPageCode();
        return out;
    }
    public String buildPageDisp() {
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
        out.addAll(buildFlipCode());
        out.addAll(buttonBuilder());
        out.add("Goto " + key);
        return out;
    }
    //builds backend of page
    public ArrayList<String> buildFlipCode() {
        ArrayList<String> out = new ArrayList<>();
        if (hasPrevious) {
            out.add(Button.previousButton(previous));
        }
        if (hasNext) {
            out.add(Button.nextButton(next));
        }
        return out;
    }
    public ArrayList<String> buildButtonListener() {
        ArrayList<String> out = new ArrayList<String>();
        out.add("Repeat K≠0");
        out.add("getKey→K");
        out.add("end");
        return out;
    }
    //adds all number buttons
    public ArrayList<String> buttonBuilder(){
        ArrayList<String> out = new ArrayList<String>();
        for (Button i : buttons) {
            out.add(i.makeButtonCode());
        }
        return out;
    }
}