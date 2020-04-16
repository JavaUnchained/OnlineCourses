import java.io.File;

public class FileClass {
    public static void main(String[] args) {
        File foldr = new File("C:\\");

        for (File file : foldr.listFiles()) {
            System.out.println(file.getName());
            System.out.println("--" + file.listFiles());
        }
    }
}
