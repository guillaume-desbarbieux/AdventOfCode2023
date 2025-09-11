import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

        game.id = Utils.getNumbersFromString(gameId.toString()).get(0).intValue();
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
        List<Character> currentColor = new ArrayList<>();

        for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
        if (Character.isDigit(input[inputIndex]))
            currentNumber.append(input[inputIndex]);

        if (Character.isAlphabetic(input[inputIndex]))
            currentColor.add(input[inputIndex]);

        if (input[inputIndex] == ',') {
            if (...){
                set.red = Utils.getNumbersFromString(currentNumber.toString()).get(0).intValue();
            }
            if (...){
                set.green = Utils.getNumbersFromString(currentNumber.toString()).get(0).intValue();
            }
            if (...){
                set.blue = Utils.getNumbersFromString(currentNumber.toString()).get(0).intValue();
            }
            currentNumber = new StringBuilder();
            currentColor = new ArrayList<>();
        }
        return set;
    }








            if (input[inputIndex] == ':') {
                inputIndex++;
                break;
            }
        }
        


        }


        String[] splitId = line.split(":");

        game.id = Integer.parseInt(splitId[0].substring(5));

        String[] splitSets = splitId[1].split(";");

        for (String set : splitSets) {
            String[] splitColors = set.split(",");
            for (String color : splitColors) {
                color = color.trim();

                String[] parts = color.split(" ");
                int count = Integer.parseInt(parts[0]);

                if ("red".equals(parts[1])) {
                    if (game.red < count) game.red = count;
                } else if ("green".equals(parts[1])) {
                    if (game.green < count) game.green = count;
                } else if ("blue".equals(parts[1])) {
                    if (game.blue < count) game.blue = count;
                }
            }
        }


        return game;
    }
}

