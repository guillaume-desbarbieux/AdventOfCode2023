import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Part2 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/ScratchCards.txt");
        List<Day4Part1.Card> cards = new ArrayList<>();

        for (int lineIndex = 0 ; lineIndex < lines.size() ; lineIndex++){
            String line = lines.get(lineIndex);
            Day4Part1.Card card = Day4Part1.getCardFromLine(line);

            if (card != null) {
                card.id = lineIndex + 1;
                card.MAX_ID = lines.size();
                cards.add(card);
            }
        }

        for (Day4Part1.Card card : cards) {
            List<Integer> ids = card.getIdsToCopy();
            for (Integer id : ids) {
                cards.get(id - 1).numberOfCopies+= card.numberOfCopies;
            }
        }

        int quantity = 0;
        for (Day4Part1.Card card : cards) {
            quantity += card.numberOfCopies;
        }
        System.out.println(quantity);
    }
}