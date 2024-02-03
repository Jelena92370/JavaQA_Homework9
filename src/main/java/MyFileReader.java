import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;

public class MyFileReader {
    public static void main(String[] args) {


        String path = "C:\\Users\\Arnaud\\Desktop\\TESTEUR\\BackEnd\\JavaQA_Homework8\\src\\main\\java\\text.txt";
        String stringFromFile = readDataFromFile(path);
        System.out.println(stringFromFile);

        String outputPath = "C:\\Users\\Arnaud\\Desktop\\TESTEUR\\BackEnd\\JavaQA_Homework8\\src\\main\\java\\output.txt";

        Map<Character, Integer> charCountMap = countCharacters(stringFromFile);
        writeCharacterCounts(charCountMap, outputPath);
    }

    private static String readDataFromFile(String path) {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(new File(path))) {
            char[] buffer = new char[2024];


            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, charsRead);
            }
        } catch (IOException exception) {
            System.out.println("We have a problem to read file " + path);
            System.out.println("Problem is " + exception.getMessage());
        }
        return sb.toString();
    }

    private static Map<Character, Integer> countCharacters(String text) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
            }
        }
        return charCountMap;
    }

    private static void writeCharacterCounts(Map<Character, Integer> charCountMap, String outputPath) {
        try (FileWriter writer = new FileWriter(new File(outputPath))) {
            for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException exception) {
            System.out.println("We have a problem to write to file " + outputPath);
            System.out.println("Problem is " + exception.getMessage());
        }
    }
}

