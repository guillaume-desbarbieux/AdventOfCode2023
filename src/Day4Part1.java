import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Part1 {
    public static void main(String[] args) {
        List<String> lines = readFile("ressources/ScratchCards.txt");
        List<Card> cards = new ArrayList<>();

        for (String line : lines) {
            Card card = getCardFromLine(line);
            if (card != null) cards.add(card);
        }
        int sum = 0;
        for (Card card : cards) {
            sum += card.points;
        }
        System.out.println(sum);
    }

    public static class Card {
        List<Integer> winningNumbers;
        List<Integer> numbers;
        List<Integer> goodNumbers;
        int points;

        public Card(List<Integer> winningNumbers, List<Integer> numbers) {
            this.winningNumbers = winningNumbers;
            this.numbers = numbers;
            this.goodNumbers = new ArrayList<>();
            checkNumbers();
            this.points = (int) Math.pow(2,goodNumbers.size()-1);
        }

        public void checkNumbers() {
            for (Integer number : numbers) {
                if (winningNumbers.contains(number))
                    this.goodNumbers.add(number);
            }
        }
    }

    public static Card getCardFromLine(String line) {
        String trimmedLine = line.trim();
        if (trimmedLine.isEmpty()) return null;
        String[] splitId = line.split(":");

        String[] splitWinnings = splitId[1].split("\\|");
        List<Integer> winningNumbers = getNumbersFromString(splitWinnings[0]);
        List<Integer> numbers = getNumbersFromString(splitWinnings[1]);
        return new Card(winningNumbers, numbers);
    }

    public static List<Integer> getNumbersFromString(String string) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : string.split(" ")) {
            if (!number.isEmpty())
                numbers.add(Integer.parseInt(number));
        }
        return numbers;
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


}