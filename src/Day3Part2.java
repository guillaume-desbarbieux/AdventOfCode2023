import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3Part2 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/Engine.txt");

        List<List<Integer>> starSymbols = new ArrayList<>();
        List<Day3Part1.Number> numbers = new ArrayList<>();

        int index = 0;
        for (String line : lines) {
            starSymbols.add(Day3Part1.getLineSymbol(line));
            List<Day3Part1.Number> numbersLine = Day3Part1.getLineNumber(line);
            for (Day3Part1.Number number : numbersLine) {
                number.line = index;
                numbers.add(number);
            }
            index++;
        }

        int sum = 0;
        for (int line = 0; line < starSymbols.size(); line++) {
            List<Integer> lineSymbols = starSymbols.get(line);

            for (int symbolIndex : lineSymbols) {
                Day3Part1.Number firstNeighbour = null;
                Day3Part1.Number secondNeighbour = null;
                boolean twoManyNeighbours = false;
                for (Day3Part1.Number number : numbers) {
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
}