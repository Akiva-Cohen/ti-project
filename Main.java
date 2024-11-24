public class Main {
    public static void main(String[] args) {
        Line[] lines = new Line[10];
        for (int i = 0; i < 10; i++){
            lines[i] = new Line("this is line number" + i);
        }
        char[] code = {'A', 'B'};
        Page mine = new Page(lines, code);
        System.out.println(mine.buildPage());
    }
}
