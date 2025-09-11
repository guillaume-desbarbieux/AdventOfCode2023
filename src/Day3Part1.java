import java.util.ArrayList;
import java.util.List;

public class Day3Part1 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/Engine.txt");


        List<List<Integer>> symbols = new ArrayList<>();
        List<Number> numbers = new ArrayList<>();

        int index = 0;
        for (String line : lines) {
            symbols.add(getLineSymbol(line));
            List<Number> numbersLine = getLineNumber(line);
            for (Number number : numbersLine) {
                number.line = index;
                numbers.add(number);
            }
            index++;
        }

        int sum = 0;
        for (int lineNumber = 0; lineNumber < symbols.size(); lineNumber++) {
            List<Integer> lineSymbols = symbols.get(lineNumber);

            for (int symbolIndex : lineSymbols) {
                for (Number number : numbers) {
                    if (number.used) continue;
                    if (number.line == lineNumber || number.line == lineNumber - 1 || number.line == lineNumber + 1) {
                        if (number.positions.contains(symbolIndex) ||
                                number.positionsContains(symbolIndex - 1) ||
                                number.positionsContains(symbolIndex + 1)) {
                            sum += number.value;
                            number.used = true;
                        }
                    }
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

        public boolean positionsContains(int number) {
            return Utils.contains(positions, number);
        }
    }

    public static List<Integer> getLineSymbol(String line) {
        List<Integer> symbols = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c != '.' && !Character.isDigit(c))  {
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