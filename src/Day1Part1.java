import java.util.List;

public class Day1Part1 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/calibration.txt");
        int sum = 0;
        for (String line : lines) {
            List<Integer> digits = Utils.getDigitsFromString(line);
            int calibration = digits.get(0) * 10 + digits.get(digits.size() - 1);
            sum += calibration;
        }
        System.out.println(sum);
    }
}