import java.util.ArrayList;
import java.util.List;

public class Day7 {

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/camelCards.txt");

        List<String> hands = new ArrayList<>();
        List<Integer> strengths = new ArrayList<>();
        List<Integer> bids = new ArrayList<>();

        for (String line : lines) {
            List<String> splitLine = Utils.split(line, ' ');

            hands.add(splitLine.get(0));
            strengths.add(getStrength(splitLine.get(0)));
            bids.add(Utils.getIntFromString(splitLine.get(1)));

            System.out.println(hands.get(hands.size() - 1) + " " + strengths.get(strengths.size() - 1) + " " + bids.get(bids.size() - 1));
        }

    }

    public static int getStrength(String stringHand) {
        char[] hand = stringHand.toCharArray();
        char[] cards = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        int[] groupedHand = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (char card : hand) {
            int index = Utils.indexOf(card, cards);
            if (index != -1) groupedHand[index]++;
        }
        return Utils.sumOfSquare(groupedHand);
    }
}
