import controller.ReadabilityController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("data/text.txt"))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            new ReadabilityController().analyzeText(sb.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}