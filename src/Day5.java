import java.util.ArrayList;
import java.util.List;

public class Day5 {

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/seed.txt");

        List<Long> seeds = Utils.getNumbersFromString(lines.get(0));

        List<List<Long>> map = new ArrayList<>();
        List<List<List<Long>>> maps = new ArrayList<>();

        for (int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
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

        Long minLocation = Long.MAX_VALUE;

        for (Long seed : seeds) {
            Long location = getLocation(maps, seed);
            if (location < minLocation) minLocation = location;
        }
        System.out.println("Part1 : " + minLocation);


        ///  Partie 2 ///


        List<SeedRange> ranges = new ArrayList<>();
        for (int pairIndex = 0; pairIndex < seeds.size(); pairIndex += 2) {
            ranges.add(new SeedRange(seeds.get(pairIndex), seeds.get(pairIndex) + seeds.get(pairIndex + 1), 0));
        }
        for (SeedRange range : ranges) {
            System.out.println("Range on map 0 [" + range.firstSeed + " ; " + range.lastSeed + "]");
        }

        for (int mapIndex = 1; mapIndex <= maps.size(); mapIndex++) {
            ranges = applyMap(maps.get(mapIndex - 1), mapIndex, ranges);

            Long seedQuantity = 0L;
            Long maxSeed = 0L;
            for (SeedRange range : ranges) {
                seedQuantity += range.lastSeed - range.firstSeed + 1;
                if (range.lastSeed > maxSeed) maxSeed = range.lastSeed;
                //System.out.println("Range on map " + range.mapIndex + " [" + range.firstSeed + " ; " + range.lastSeed + "]");
                range.mapIndex = mapIndex;
            }
            System.out.println("");
            System.out.println("After map " + mapIndex + " : " + ranges.size() + " ranges, " + seedQuantity + " seeds, " + maxSeed + " max ID");
        }

        minLocation = Long.MAX_VALUE;

        for (SeedRange range : ranges) {
            if (range.firstSeed < minLocation) minLocation = range.firstSeed;
        }
        System.out.println("Part2 : " + minLocation);
    }

    public static List<SeedRange> applyMap(List<List<Long>> map, int mapIndex, List<SeedRange> ranges) {
        List<SeedRange> resolvedRanges = new ArrayList<>();
        List<SeedRange> workingRanges = new ArrayList<>();

        for (SeedRange range : ranges) {
            if (range.mapIndex >= mapIndex) resolvedRanges.add(range);
            else workingRanges.add(range);
        }
        for (List<Long> line : map) {
            List<SeedRange> newRanges = applyLine(line, mapIndex, workingRanges);

            workingRanges = new ArrayList<>();
            for (SeedRange newRange : newRanges) {
                if (newRange.mapIndex == mapIndex)
                    resolvedRanges.add(newRange);
                else workingRanges.add(newRange);
            }
        }
        if (workingRanges.size() > 0) resolvedRanges.addAll(workingRanges);
        return resolvedRanges;
    }

    public static List<SeedRange> applyLine(List<Long> line, int mapIndex, List<SeedRange> ranges) {
        List<SeedRange> result = new ArrayList<>();
        for (SeedRange range : ranges) {
            long start = line.get(1);
            long end = start + line.get(2)-1;
            long shift = line.get(0) - start;

            if (range.firstSeed > end || range.lastSeed < start) {
                result.add(range);
                continue;
            }

            if (range.firstSeed < start) {
                result.add(new SeedRange(range.firstSeed, start - 1, mapIndex - 1));

                if (range.lastSeed <= end)
                    result.add(new SeedRange(start + shift, range.lastSeed + shift, mapIndex));
                else {
                    result.add(new SeedRange(start + shift, end + shift, mapIndex));
                    result.add(new SeedRange(end + 1, range.lastSeed, mapIndex - 1));
                }
            } else if (range.lastSeed <= end)
                result.add(new SeedRange(range.firstSeed + shift, range.lastSeed + shift, mapIndex));
            else {
                result.add(new SeedRange(range.firstSeed + shift, end + shift, mapIndex));
                result.add(new SeedRange(end + 1, range.lastSeed, mapIndex - 1));
            }
        }
        return result;
    }

    public static class SeedRange {
        long firstSeed;
        long lastSeed;
        int mapIndex;

        public SeedRange(long firstSeed, long lastSeed, int mapIndex) {
            this.firstSeed = firstSeed;
            this.lastSeed = lastSeed;
            this.mapIndex = mapIndex;
        }
    }

    public static Long getLocation(List<List<List<Long>>> maps, Long seed) {
        for (List<List<Long>> currentMap : maps) {

            boolean found = false;
            for (int lineIndex = 0; lineIndex < currentMap.size() && !found; lineIndex++) {

                List<Long> currentLine = currentMap.get(lineIndex);

                Long startDestination = currentLine.get(0);
                Long startSource = currentLine.get(1);
                Long rangeLength = currentLine.get(2);

                if (seed >= startSource && seed <= startSource + rangeLength - 1) {
                    seed += startDestination - startSource;
                    found = true;
                }
            }
        }
        return seed;
    }
}
