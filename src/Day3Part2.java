import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3Part2 {
    public static void main(String[] args) {
        List<String> lines = readFile("ressources/Engine.txt");
        List<List<Integer>> starSymbols = new ArrayList<>();
        List<Number> numbers = new ArrayList<>();

        int index = 0;
        for (String line : lines) {
            starSymbols.add(getLineSymbol(line));
            List<Number> numbersLine = getLineNumber(line);
            for (Number number : numbersLine) {
                number.line = index;
                numbers.add(number);
               // System.out.println("Line " + number.line + " : " + number.value + " : " + number.positions);
            }
            index++;
        }

        int sum = 0;
        for (int line = 0; line < starSymbols.size(); line++) {
            List<Integer> lineSymbols = starSymbols.get(line);

            for (int symbolIndex : lineSymbols) {
                Number firstNeighbour = null;
                Number secondNeighbour = null;
                boolean twoManyNeighbours = false;
                for (Number number : numbers) {
                    if (number.used) continue;
                    if (number.line == line || number.line == line - 1 || number.line == line + 1) {
                        if (number.positions.contains(symbolIndex) ||
                                number.positions.contains(symbolIndex - 1) ||
                                number.positions.contains(symbolIndex + 1)) {
                            if (firstNeighbour == null) firstNeighbour = number;
                            else if (secondNeighbour == null) secondNeighbour = number;
                            else twoManyNeighbours = true;
                        }
                    }
                }
                if (twoManyNeighbours) continue;
                if (firstNeighbour != null && secondNeighbour != null) {
                    sum += firstNeighbour.value * secondNeighbour.value;
                }
            }
        }
        System.out.println(sum);
    }

    public static class Number {
        int value;
        int line;
        List<Integer> positions;
        boolean used;

        public Number(int value, List<Integer> positions) {
            this.value = value;
            this.positions = positions;
            this.used = false;
        }
    }

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

    public static List<Integer> getLineSymbol(String line) {
        List<Integer> symbols = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '*')  {
                symbols.add(i);
            }
        }
        return symbols;
    }

    public static List<Number> getLineNumber(String line) {
        List<Number> numbers = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) {
                List<Integer> digits = new ArrayList<>();
                List<Integer> positions = new ArrayList<>();


                while (i < line.length() && Character.isDigit(line.charAt(i))) {
                    digits.add(Character.getNumericValue(line.charAt(i)));
                    positions.add(i);
                    i++;
                }

                int value = 0;
                for (int digit : digits) {
                    value = value * 10 + digit;
                }

                numbers.add(new Number(value, positions));
            }
        }
        return numbers;
    }
}