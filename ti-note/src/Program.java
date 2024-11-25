import java.io.FileWriter;
public class Program {
    Page[] pages;
    public Program(Page[] pages){
        this.pages = pages;
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
