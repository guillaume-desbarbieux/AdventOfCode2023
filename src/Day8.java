import jdk.jshell.execution.Util;

import java.util.Arrays;
import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/hauntedWasteland.txt");

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
        System.out.println(counter);
    }
}