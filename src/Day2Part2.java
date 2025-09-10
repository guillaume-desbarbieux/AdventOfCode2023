import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2Part2 {
    public static void main(String[] args) {
        List<Game> games = readFile("ressources/gameCube.txt");

        int sumPower = 0;
        for (Game game : games) {
            sumPower += game.red * game.green * game.blue;
        }

        System.out.println(sumPower);

    }

    public static class Game {
        int id;
        int red;
        int green;
        int blue;

        public Game() {
        }
    }

    public static List<Game> readFile(String path)  {
        List<Game> games = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] splitId = line.split(":");
                Game game = new Game();
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
                        }
                        else if ("green".equals(parts[1])) {
                            if (game.green < count) game.green = count;
                        }
                        else if ("blue".equals(parts[1])) {
                            if (game.blue < count) game.blue = count;
                        }
                        }
                }
                games.add(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return games;
    }
}