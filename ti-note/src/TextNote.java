import java.util.*;
public class TextNote extends FlipBook {
    public TextNote(String text) {
        super(stringToNote(text));
    }
    //one method to rule them all
    public static String submitTextNote(String text, boolean isLoop) {
        TextNote note = new TextNote(text);
        String code = note.programBuild(isLoop);
        return code;
    }
    public static Line[] textToLinesClassArray(String text) {
        ArrayList<Line> lines = textToLinesClass(text);
        Line[] out = new Line[lines.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = lines.get(i);
        }
        return out;
    }
    public static ArrayList<Line> textToLinesClass(String text) {
        ArrayList<String[]> wordLines = textToLines(text);
        ArrayList<String> mainLines = linesToLines(wordLines);
        ArrayList<Line> linesClass = linesToLineClass(mainLines);
        return linesClass;
    }
    public static Page[] stringToNote(String text) {
        ArrayList<Line> linesCLass = textToLinesClass(text);
        ArrayList<Page> pages = lineClassToPages(linesCLass);
        Page[] out = new Page[pages.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = pages.get(i);
        }
        return out;
    }
    public static ArrayList<Page> lineClassToPages(ArrayList<Line> lines) {
        ArrayList<Page> out = new ArrayList<>();
        int count = 0;
        ArrayList<Line> mem = new ArrayList<>();
        int ii = 0;
        for (Line i : lines){
            if (count < 10) {
                mem.add(i);
                count++;
            } else {
                Line[] pgArr = new Line[mem.size()];
                for (int b = 0; b < pgArr.length; b++) {
                    pgArr[b] = mem.get(b);
                }
                out.add(new Page(pgArr, Code.intToCode(ii)));
                mem = new ArrayList<>();
                mem.add(i);
                count = 1;
                ii++;
            }
        }
        Line[] pgArr = new Line[mem.size()];
        for (int i = 0; i < pgArr.length; i++) {
            pgArr[i] = mem.get(i);
        }
        out.add(new Page(pgArr, Code.intToCode(ii)));
        return out;
    }
    public static ArrayList<Line> linesToLineClass(ArrayList<String> wordLines) {
        ArrayList<Line> out = new ArrayList<>();
        for (String i : wordLines) {
            out.add(new Line(i));
        }
        return out;
    }
    //converts from arrays of origional lines to an array of every line needed
    public static ArrayList<String> linesToLines(ArrayList<String[]> wordLines) {
        ArrayList<String> out = new ArrayList<>();
        for (String[] words : wordLines) {
            out.addAll(wordsToLines(words));
        }
        return out;
    }
    public static ArrayList<String> wordsToLines(String[] words) {
        ArrayList<String> out = new ArrayList<>();
        String currentLine = "";
        for (String word : words) {
            if (currentLine.length() == 0) {
                currentLine += word;
            } else {
                if (currentLine.length() + 1 + word.length() <= 26) {
                    currentLine += " " + word;
                } else {
                    out.add(currentLine);
                    currentLine = word;
                }
            }
        }
        out.add(currentLine);
        return out;
    }
    public static ArrayList<String[]> textToLines(String text) {
        String[] lines = text.split("[\n]");
        ArrayList<String[]> wordLines = new ArrayList<>();
        for (String line : lines) {
            wordLines.add(textToWords(line));
        }
        return wordLines;
    }
    public static String[] textToWords(String text) {
        String[] words = text.split("[ ]");
        return words;
    }
}
