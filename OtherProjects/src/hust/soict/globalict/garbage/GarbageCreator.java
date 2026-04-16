package hust.soict.globalict.garbage;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) throws Exception {
        String filename = "test.exe"; 
        byte[] inputBytes = Files.readAllBytes(Paths.get(filename));
        long start = System.currentTimeMillis();
        StringBuilder outputStringBuilder = new StringBuilder();
        for (byte b : inputBytes) {
            outputStringBuilder.append((char)b);
        }
        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }
}