import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Part1 {
    public static void main(String[] args) {
        System.out.println(getCalibration("ressources/calibration.txt"));
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
        System.out.println(line);
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i)))
                digits.add(Character.getNumericValue(line.charAt(i)));
        }
        System.out.println(digits);
        int code = digits.get(0) * 10 + digits.get(digits.size() - 1);
        System.out.println(code);
        return code;
    }
}