import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Part2 {
    public static void main(String[] args) {
        System.out.println(getCalibration("ressources/calibration.txt"));

        System.out.println( getLineCode("one1twoneone"));
    }

    public static int getCalibration(String path)  {
        int sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                sum += getLineCode(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
        return sum;
    }

    public static int getLineCode(String line) {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (Character.isDigit(c))
                digits.add(Character.getNumericValue(c));

            else {
                if (line.startsWith("one", i))
                    digits.add(1);
                if (line.startsWith("two", i))
                    digits.add(2);
                if (line.startsWith("six", i))
                    digits.add(6);
                if (line.startsWith("four", i))
                    digits.add(4);
                if (line.startsWith("five", i))
                    digits.add(5);
                if (line.startsWith("nine", i))
                    digits.add(9);
                if (line.startsWith("zero", i))
                    digits.add(0);
                if (line.startsWith("three", i))
                    digits.add(3);
                if (line.startsWith("seven", i))
                    digits.add(7);
                if (line.startsWith("eight", i))
                    digits.add(8);
            }
        }
        return digits.get(0) * 10 + digits.get(digits.size() - 1);
    }
}