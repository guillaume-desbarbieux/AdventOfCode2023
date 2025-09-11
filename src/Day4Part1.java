import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Part1 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/ScratchCards.txt");
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
        int id;
        List<Integer> winningNumbers;
        List<Integer> numbers;
        List<Integer> goodNumbers;
        int points;
        int numberOfCopies;
        int MAX_ID = 0;

        public Card(List<Integer> winningNumbers, List<Integer> numbers) {
            this.winningNumbers = winningNumbers;
            this.numbers = numbers;
            this.goodNumbers = new ArrayList<>();
            checkNumbers();
            this.points = (int) Math.pow(2,goodNumbers.size()-1);
            this.numberOfCopies = 1;
        }

        public Card(int id, List<Integer> winningNumbers, List<Integer> numbers) {
            this.id = id;
            this.winningNumbers = winningNumbers;
            this.numbers = numbers;
            this.goodNumbers = new ArrayList<>();
            checkNumbers();
            this.numberOfCopies = 1;
        }

        public void checkNumbers() {
            for (Integer number : numbers) {
                if (Utils.contains(winningNumbers, number))
                    this.goodNumbers.add(number);
            }
        }

        public List<Integer> getIdsToCopy() {
            List<Integer> ids = new ArrayList<>();
            for (int i = 1; i <= goodNumbers.size(); i++) {
                if (id + i > MAX_ID) break;
                ids.add(id + i);
            }
            return ids;
        }
    }

    public static Card getCardFromLine(String line) {
        if (line.isEmpty()) return null;
        List<String> splitId = Utils.split(line, ':');
        List<String> splitWinnings = Utils.split(splitId.get(1), '|');

        List<Integer> winningNumbers = getNumbersFromString(splitWinnings.get(0));
        List<Integer> numbers = getNumbersFromString(splitWinnings.get(1));
        return new Card(winningNumbers, numbers);
    }

    public static List<Integer> getNumbersFromString(String string) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : Utils.split(string, ' ')) {
            if (!number.isEmpty())
                numbers.add(Integer.parseInt(number));
        }
        return numbers;
    }

}