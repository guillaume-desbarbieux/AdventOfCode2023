import java.util.ArrayList;
import java.util.List;

public class Day2Part2 {

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/gameCube.txt");
        List<Day2Part1.Game> games = new ArrayList<>();

        for (String line : lines) {
            games.add(Day2Part1.getGameFromString(line));
        }

        int sumPower = 0;
        for (Day2Part1.Game game : games) {
            sumPower += game.red * game.green * game.blue;
        }

        System.out.println(sumPower);
    }

}