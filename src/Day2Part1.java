import java.util.ArrayList;
import java.util.List;

public class Day2Part1 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/gameCube.txt");
        List<Game> games = new ArrayList<>();

        for (String line : lines) {
            games.add(getGameFromString(line));
        }

        int sumId = 0;
        for (Game game : games) {
            if (game.red <= 12 && game.green <= 13 && game.blue <= 14)
                sumId += game.id;
        }
        System.out.println(sumId);
    }

    public static class Game {
        int id;
        int red;
        int green;
        int blue;

        public Game() {
        }
    }

    public static class Set {
        int red;
        int green;
        int blue;

        public Set() {
        }
    }

    public static Game getGameFromString(String line) {

        char[] input = line.toCharArray();

        StringBuilder gameId = new StringBuilder();
        boolean foundGameId = false;

        StringBuilder currentSet = new StringBuilder();
        boolean foundSet = false;

        List<String> Sets = new ArrayList<>();

        for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
            if (input[inputIndex] == ':')
                foundGameId = true;

            if (input[inputIndex] == ';')
                foundSet = true;

            if (!foundGameId)
                gameId.append(input[inputIndex]);

            if (foundGameId && !foundSet)
                currentSet.append(input[inputIndex]);

            if (foundSet) {
                Sets.add(currentSet.toString());
                currentSet = new StringBuilder();
                foundSet = false;
            }

        }

        if (currentSet.length() > 0)
            Sets.add(currentSet.toString());

        Game game = new Game();

        game.id = Utils.getIntFromString(gameId.toString());
        game.red = 0;
        game.green = 0;
        game.blue = 0;

        for (String set : Sets) {
            Set newSet = getSetFromString(set);
            if (newSet.red > game.red) game.red = newSet.red;
            if (newSet.green > game.green) game.green = newSet.green;
            if (newSet.blue > game.blue) game.blue = newSet.blue;
        }

        return game;
    }

    public static Set getSetFromString(String line) {
        Set set = new Set();
        set.red = 0;
        set.green = 0;
        set.blue = 0;

        char[] input = line.toCharArray();

        StringBuilder currentNumber = new StringBuilder();
        StringBuilder currentColor = new StringBuilder();

        for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
            if (Character.isDigit(input[inputIndex]))
                currentNumber.append(input[inputIndex]);

            if (Character.isAlphabetic(input[inputIndex]))
                currentColor.append(input[inputIndex]);

            if (input[inputIndex] == ',') {
                String color = currentColor.toString();
                String number = currentNumber.toString();
                if (Utils.compareStrings(color, "red")) {
                    set.red = Utils.getIntFromString(number);
                }
                if (Utils.compareStrings(color, "green")) {
                    set.green = Utils.getIntFromString(number);
                }
                if (Utils.compareStrings(color, "blue")) {
                    set.blue = Utils.getIntFromString(number);
                }
                currentNumber = new StringBuilder();
                currentColor = new StringBuilder();
            }
        }

        return set;
    }
}