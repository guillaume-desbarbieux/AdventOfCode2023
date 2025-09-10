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
        Game game = new Game();
        char[] input = line.toCharArray();
        
        String gameId = "";
        List<Set> gameSets = new ArrayList<>();


        for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
            if





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

