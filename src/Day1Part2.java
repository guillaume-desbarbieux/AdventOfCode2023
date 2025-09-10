import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Part2 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/calibration.txt");

        String[] dictSource = {"zero","one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] dictTarget = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9"};

        int sum = 0;

        for (String line : lines) {
            String newLine = Utils.replaceUsingDict(line, dictSource, dictTarget);
            List<Integer> digits = Utils.getDigitsFromString(newLine);
            int calibration = digits.get(0) * 10 + digits.get(digits.size() - 1);
            sum += calibration;
        }
        System.out.println(sum);
    }
}