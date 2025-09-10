import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Part2 {
    static int MAX_ID = 0;
    public static void main(String[] args) {
        List<String> lines = readFile("ressources/ScratchCards.txt");
        List<Card> cards = new ArrayList<>();

        MAX_ID = lines.size();

        for (String line : lines) {
            Card card = getCardFromLine(line);
            if (card != null) cards.add(card);
        }

        for (Card card : cards) {
            List<Integer> ids = card.getIdsToCopy();
            for (Integer id : ids) {
                cards.get(id - 1).numberOfCopies+= card.numberOfCopies;
            }
        }

        int quantity = 0;
        for (Card card : cards) {
            quantity += card.numberOfCopies;
        }
        System.out.println(quantity);
    }

    public static class Card {
        int id;
        List<Integer> winningNumbers;
        List<Integer> numbers;
        List<Integer> goodNumbers;
        int numberOfCopies;

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
                if (winningNumbers.contains(number))
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
        String trimmedLine = line.trim();
        if (trimmedLine.isEmpty()) return null;
        String[] splitId = line.split(":");
        int id = Integer.parseInt(splitId[0].substring(5).trim());

        String[] splitWinnings = splitId[1].split("\\|");
        List<Integer> winningNumbers = getNumbersFromString(splitWinnings[0]);
        List<Integer> numbers = getNumbersFromString(splitWinnings[1]);
        return new Card(id, winningNumbers, numbers);
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