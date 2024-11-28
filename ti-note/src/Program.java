import java.io.FileWriter;
import java.util.ArrayList;
public class Program {
    Page[] pages;
    public Program(Page[] pages){
        this.pages = pages;
    }
    public String programBuild() {
        String out = "";
        for (Page i : pages) {
            out += i.buildPage() + "\n";
        }
        out += "Lbl θ\nClrHome";
        return out;
    }
    public boolean checkConections() {
        ArrayList<Code> destinations = new ArrayList<>();
        ArrayList<Code> keys = new ArrayList<>();
        for (Page page : pages) {
            keys.add(page.getKey());
            for (Button button : page.getButtons()) {
                destinations.add(button.getDestination());
            }
        }
        for (Code destination : destinations) {
            boolean isIn = false;
            for (Code key : keys) {
                if (destination.equals(key)) {
                    isIn = true;
                }
            }
            if (!isIn) {
                return false;
            }
        }
        return true;

    }
    public Page[] getPages() {
        return pages;
    }
    public static void toTxt(String text) {
        try {
        FileWriter writer = new FileWriter("output.txt");
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
