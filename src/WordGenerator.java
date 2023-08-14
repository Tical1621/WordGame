import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordGenerator {
    private List<String> words = new ArrayList<>();

    public String generateWord() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(
                        "C:\\Users\\Java\\Desktop\\javaProject\\eduProjects\\WordGame\\src\\russian_nouns.txt")
        )) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
            return words.get((int) (Math.random() * words.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
