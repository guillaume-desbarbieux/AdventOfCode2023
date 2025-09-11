import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5Part1 {

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/seed.txt");

        List<Long> seeds = Utils.getNumbersFromString(lines.get(0));

        List<List<Long>> map = new ArrayList<>();
        List<List<List<Long>>> maps = new ArrayList<>();

        for (int lineIndex = 1 ; lineIndex < lines.size() ; lineIndex++) {
            String line = lines.get(lineIndex);

            if (line.length() > 0) {

                if (Utils.contains(line, ':')) {
                    if (map.size() > 0) {
                        maps.add(map);
                        map = new ArrayList<>();
                    }
                } else map.add(Utils.getNumbersFromString(line));
            }
        }
        maps.add(map);

        List<Long> locations = new ArrayList<>();
        Long minLocation = Long.MAX_VALUE;

        for (int seedIndex = 0 ; seedIndex < seeds.size() ; seedIndex++) {
            Long location = seeds.get(seedIndex);

            for (List<List<Long>> currentMap : maps) {

                boolean found = false;
                for (int lineIndex = 0 ; lineIndex < currentMap.size() && !found ; lineIndex++) {

                    List<Long> currentLine = currentMap.get(lineIndex);

                    Long startDestination = currentLine.get(0);
                    Long startSource = currentLine.get(1);
                    Long rangeLength = currentLine.get(2);

                    if (location >= startSource && location <= startSource + rangeLength) {
                        location += startDestination - startSource;
                        found = true;
                    }
                }
            }
            locations.add(location);
            if (location < minLocation) minLocation = location;
        }

        System.out.println( minLocation);
    }
}