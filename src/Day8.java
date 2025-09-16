
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/hauntedWasteland.txt");

        System.out.println(part1(lines));

        List<Integer> counters = part2(lines);
        BigInteger ppcm = BigInteger.valueOf(1);
        for (Integer counter : counters) {
            ppcm = Utils.ppcm(ppcm, BigInteger.valueOf(counter));
        }
        System.out.println(ppcm);
    }

    public static int part1(List<String> lines) {

        char[] instructions = lines.get(0).toCharArray();
        char[][][] nodes = new char[lines.size() - 2][3][3];
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            char[] name = {line.charAt(0), line.charAt(1), line.charAt(2)};
            char[] left = {line.charAt(7), line.charAt(8), line.charAt(9)};
            char[] right = {line.charAt(12), line.charAt(13), line.charAt(14)};
            nodes[i - 2][0] = name;
            nodes[i - 2][1] = left;
            nodes[i - 2][2] = right;
        }
        int[][] numericNodes = new int[nodes.length][3];
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < nodes.length; i++) {

            int left = -1;
            int right = -1;

            for (int j = 0; j < nodes.length && (left == -1 || right == -1); j++) {
                if (Utils.isSame(nodes[j][0], nodes[i][1])) left = j;
                if (Utils.isSame(nodes[j][0], nodes[i][2])) right = j;
                if (Utils.isSame(nodes[j][0], new char[]{'Z', 'Z', 'Z'})) endIndex = j;
                if (Utils.isSame(nodes[j][0], new char[]{'A', 'A', 'A'})) startIndex = j;
            }
            numericNodes[i] = new int[]{i, left, right};
        }


        int counter = 0;
        int modulo = instructions.length;

        int[] current = numericNodes[startIndex];
        while (current[0] != endIndex) {
            char direction = instructions[counter % modulo];
            counter++;

            switch (direction) {
                case 'L' -> current = numericNodes[current[1]];
                case 'R' -> current = numericNodes[current[2]];
            }
        }
        return counter;
    }

    public static List<Integer> part2(List<String> lines) {

        int[] directions = new int[lines.get(0).length()];
        for (int i = 0; i < directions.length; i++) {
            if (lines.get(0).charAt(i) == 'L') directions[i] = 1;
            else directions[i] = 2;
        }

        char[][][] nodes = new char[lines.size() - 2][3][3];

        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            char[] name = {line.charAt(0), line.charAt(1), line.charAt(2)};
            char[] left = {line.charAt(7), line.charAt(8), line.charAt(9)};
            char[] right = {line.charAt(12), line.charAt(13), line.charAt(14)};
            nodes[i - 2][0] = name;
            nodes[i - 2][1] = left;
            nodes[i - 2][2] = right;
        }

        int[][] numericNodes = new int[nodes.length][3];
        List<Integer> startIndex = new ArrayList<>();

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i][0][2] == 'A') startIndex.add(i);

            int left = -1;
            int right = -1;

            for (int j = 0; j < nodes.length; j++) {
                if (Utils.isSame(nodes[j][0], nodes[i][1])) left = j;
                if (Utils.isSame(nodes[j][0], nodes[i][2])) right = j;
            }
            numericNodes[i] = new int[]{i, left, right};
        }

        int modulo = directions.length;

        List<Integer> periods = new ArrayList<>();
        for (int start : startIndex) {
            int counter = 0;
            int[] current = numericNodes[start];
            while (nodes[current[0]][0][2] != 'Z') {
                counter++;
                current = numericNodes[current[directions[counter % modulo]]];
            }

            int startPeriod = counter;
            counter++;
            current = numericNodes[current[directions[counter % modulo]]];

            while (nodes[current[0]][0][2] != 'Z') {
                counter++;
                current = numericNodes[current[directions[counter % modulo]]];
            }


            periods.add(counter - startPeriod);
        }
        return periods;
    }
}