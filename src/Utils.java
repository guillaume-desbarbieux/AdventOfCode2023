import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> readFile(String path)  {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


    public static List<Long> getNumbersFromString(String string) {
        List<Long> numbers = new ArrayList<>();
        long number = 0;
        boolean foundNumber = false;
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c)) {
                foundNumber = true;
                number = number * 10 + Character.getNumericValue(c);
            } else {
                if (foundNumber){
                    numbers.add(number);
                    foundNumber = false;
                    number = 0;
                }
            }
        }
        return numbers;
    }

    public static List<Integer> getDigitsFromString(String string) {
        List<Integer> digits = new ArrayList<>();
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c))
                digits.add(Character.getNumericValue(c));
        }
        return digits;
    }
}
