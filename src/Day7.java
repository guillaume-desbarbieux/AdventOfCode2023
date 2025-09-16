import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {
    public static final char[] cards1 = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    public static final char[] cards2 = {'J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'};

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/camelCards.txt");
        part1(lines);
        part2(lines);

    }

    public static void part1(List<String> lines) {
        List<List<String>> typedHands = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            typedHands.add(new ArrayList<>());
        }


        for (String hand : lines) {

            int strength = getStrength(Utils.split(hand, ' ').get(0), cards1);
            switch (strength) {
                case 5 -> typedHands.get(0).add(hand);
                case 7 -> typedHands.get(1).add(hand);
                case 9 -> typedHands.get(2).add(hand);
                case 11 -> typedHands.get(3).add(hand);
                case 13 -> typedHands.get(4).add(hand);
                case 17 -> typedHands.get(5).add(hand);
                case 25 -> typedHands.get(6).add(hand);
                //default -> throw new IllegalStateException("Unexpected value: " + strength);
            }
        }
        int result = 0;
        int rank = 1;
        for (List<String> type : typedHands) {
            type = sortHandsSameType(type, cards1);
            for (String hand : type) {
                int bid = Utils.getIntFromString(Utils.split(hand, ' ').get(1));
                result += bid * rank;
                rank++;
            }
        }
        System.out.println(result);
    }

    public static void part2(List<String> lines) {
        List<List<String>> typedHands = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            typedHands.add(new ArrayList<>());
        }


        for (String hand : lines) {

            int strength = getStrength2(Utils.split(hand, ' ').get(0), cards2);
            switch (strength) {
                case 5 -> typedHands.get(0).add(hand);
                case 7 -> typedHands.get(1).add(hand);
                case 9 -> typedHands.get(2).add(hand);
                case 11 -> typedHands.get(3).add(hand);
                case 13 -> typedHands.get(4).add(hand);
                case 17 -> typedHands.get(5).add(hand);
                case 25 -> typedHands.get(6).add(hand);
                //default -> throw new IllegalStateException("Unexpected value: " + strength);
            }
        }
        int result = 0;
        int rank = 1;
        for (List<String> type : typedHands) {
            type = sortHandsSameType(type, cards2);
            for (String hand : type) {
                int bid = Utils.getIntFromString(Utils.split(hand, ' ').get(1));
                result += bid * rank;
                rank++;
            }
        }
        System.out.println(result);
    }

    public static int getStrength(String stringHand, char[] cards) {
        char[] hand = stringHand.toCharArray();
        int[] groupedHand = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (char card : hand) {
            int index = Utils.indexOf(card, cards);
            if (index != -1) groupedHand[index]++;
        }
        return Utils.sumOfSquare(groupedHand);
    }
    public static int getStrength2(String stringHand, char[] cards) {
        char[] hand = stringHand.toCharArray();
        int[] groupedHand = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (char card : hand) {
            int index = Utils.indexOf(card, cards);
            if (index != -1) groupedHand[index]++;
        }
        int totalBefore = Arrays.stream(groupedHand).sum();
        int maxIndex = 1;
        for (int i = 2; i < groupedHand.length; i++) {
            if (groupedHand[i] > groupedHand[maxIndex]) maxIndex = i;
        }
        groupedHand[maxIndex] += groupedHand[0];
        groupedHand[0] = 0;


// fusion jokers
        int totalAfter = Arrays.stream(groupedHand).sum();
        //System.out.println(stringHand + " " + Utils.sumOfSquare(groupedHand) + " " + Arrays.toString(groupedHand));
        System.out.println(totalBefore + " " + totalAfter);
        assert totalBefore == totalAfter;
        return Utils.sumOfSquare(groupedHand);
    }


    public static List<String> sortHandsSameType(List<String> hands, char[] cards) {
        List<String> sortedHands = new ArrayList<>();

        while (!hands.isEmpty()) {
            int minIndex = 0;
            String minHand = hands.get(0);

            for (int i = 1; i < hands.size(); i++) {
                String current = hands.get(i);
                if (isSmaller(current, minHand, cards)) {
                    minHand = current;
                    minIndex = i;
                }
            }

            sortedHands.add(minHand);
            hands.remove(minIndex);
        }

        return sortedHands;
    }

    public static boolean isSmaller(String h1, String h2, char[] cards) {
        String hand1 = h1.split(" ")[0];
        String hand2 = h2.split(" ")[0];

        for (int i = 0; i < hand1.length(); i++) {
            int idx1 = Utils.indexOf(hand1.charAt(i), cards);
            int idx2 = Utils.indexOf(hand2.charAt(i), cards);
            if (idx1 < idx2) return true;
            if (idx1 > idx2) return false;
        }
        return false;
    }
}