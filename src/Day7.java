import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/camelCards.txt");
        char[] cards = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};

        List<List<String>> strengthHands = new ArrayList<>();
        for (String line : lines) {
            List<String> hand = Utils.split(line, ' ');
            int strength = getStrength(hand.get(0).toCharArray());
            int bid = Utils.getIntFromString(hand.get(1));


        }

    }

    public static int getStrength(char[] hand) {
        System.out.println("For hand : " + new String(hand));
        char[] cards = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        int[] orderedHand = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (char card : hand) {
            int index = Utils.indexOf(card, cards);
            System.out.println(card + " : " + index);
            if (index != -1) orderedHand[index]++;
        }

        System.out.println(Arrays.toString(orderedHand));
        return 0;
    }
}
