/**import java.util.ArrayList;
import java.util.List;

public class Day7 {

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/camelCards.txt");

        List<String> hands = new ArrayList<>();
        List<Integer> strengths = new ArrayList<>();
        List<Integer> bids = new ArrayList<>();

        List<String> FiveOfAKind = new ArrayList<>();
        List<String> FourOfAKind = new ArrayList<>();
        List<String> FullHouse = new ArrayList<>();
        List<String> ThreeOfAKind = new ArrayList<>();
        List<String> TwoPair = new ArrayList<>();
        List<String> OnePair = new ArrayList<>();
        List<String> HighCard = new ArrayList<>();

        for (String hand : lines) {

            int strength = getStrength(Utils.split(hand, ' ').get(0));
            switch (strength) {
                case 5 -> HighCard.add(hand);
                case 7 -> OnePair.add(hand);
                case 9 -> TwoPair.add(hand);
                case 11 -> ThreeOfAKind.add(hand);
                case 13 -> FullHouse.add(hand);
                case 17 -> FourOfAKind.add(hand);
                case 25 -> FiveOfAKind.add(hand);
            }
        }

        //sorting by strength

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

    public static List<String> sortHandsSameType(List<String> hands) {
        List<String> sortedHands = new ArrayList<>();
        char[] cards = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

        while (hands.size() > 1) {
            List<Integer> minIndex = new ArrayList<>();
            int minStrength = cards.length;

            for (int charIndex = 0; charIndex < hands.get(0).length(); charIndex++) {

                for (int handIndex = 0; handIndex < hands.size(); handIndex++) {
                    char[] hand = hands.get(handIndex).toCharArray();
                    int strength = Utils.indexOf(hand[charIndex], cards);
                    if (strength < minStrength) {
                        minStrength = strength;
                        minIndex = new ArrayList<>();
                        minIndex.add(handIndex);
                    } else if (strength == minStrength) {
                        minIndex.add(handIndex);
                    }
                }

                if (minIndex.size() == 1) {
                    sortedHands.add(hands.get(minIndex.get(0)));
                    hands.remove(minIndex.get(0));
                }


            }
        }


    }
        return null;
}
}
*/