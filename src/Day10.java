import java.util.ArrayList;
import java.util.List;

public class Day10 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/PipeMaze.txt");
        System.out.println(part1(lines));
    }

    public static int part1(List<String> lines) {
        int hauteur = lines.size();
        int largeur = lines.get(0).length();
        int[][][][] maze = new int[largeur][hauteur][2][2];
        int iStart = -1;
        int jStart = -1;

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                switch (lines.get(i).charAt(j)) {
                    case '|' -> maze[i][j] = new int[][]{{i - 1, j}, {i + 1, j}};
                    case '-' -> maze[i][j] = new int[][]{{i, j - 1}, {i, j + 1}};
                    case 'L' -> maze[i][j] = new int[][]{{i - 1, j}, {i, j + 1}};
                    case 'J' -> maze[i][j] = new int[][]{{i - 1, j}, {i, j - 1}};
                    case '7' -> maze[i][j] = new int[][]{{i + 1, j}, {i, j - 1}};
                    case 'F' -> maze[i][j] = new int[][]{{i + 1, j}, {i, j + 1}};
                    case 'S' -> {
                        iStart = i;
                        jStart = j;
                    }
                    default -> maze[i][j] = new int[][]{{i, j}, {i, j}};
                }
            }
        }
        int foundNeigbours = 0;
        if (iStart > 0) {
            char c = lines.get(iStart - 1).charAt(jStart);
            if (c == '|' || c == '7' || c == 'F') {
                maze[iStart][jStart][foundNeigbours] = new int[]{iStart - 1, jStart};
                foundNeigbours++;
            }
        }
        if (iStart < hauteur - 1) {
            char c = lines.get(iStart + 1).charAt(jStart);
            if (c == '|' || c == 'L' || c == 'J') {
                maze[iStart][jStart][foundNeigbours] = new int[]{iStart + 1, jStart};
                foundNeigbours++;
            }
        }
        if (jStart > 0) {
            char c = lines.get(iStart).charAt(jStart - 1);
            if (c == '-' || c == 'L' || c == 'F') {
                maze[iStart][jStart][foundNeigbours] = new int[]{iStart, jStart - 1};
                foundNeigbours++;
            }
        }
        if (jStart < largeur - 1) {
            char c = lines.get(iStart).charAt(jStart + 1);
            if (c == '-' || c == 'J' || c == '7') {
                maze[iStart][jStart][foundNeigbours] = new int[]{iStart, jStart + 1};
                foundNeigbours++;}
        }

        int counter = 0;
        int[] current = new int[]{iStart, jStart};
        int[] previous = current;
        int[] next;

        char [][] clearedMaze = new char[hauteur][largeur];
        for(int i = 0; i < hauteur; i++) {
            for(int j = 0; j < largeur; j++) {
                clearedMaze[i][j] = ' ';
            }
        }

        clearedMaze[current[0]][current[1]] = lines.get(current[0]).charAt(current[1]);
        do {
            next = getNext(maze, current, previous);
            previous = current;
            current = next;
            clearedMaze[current[0]][current[1]] = lines.get(current[0]).charAt(current[1]);
            counter++;
        } while (current[0] != iStart || current[1] != jStart);

        for (char[] line : clearedMaze) {
            for (char c : line) {
                System.out.print(c);
            }
            System.out.println();
        }
        return counter/2;
    }

    private static int[] getNext(int[][][][] maze, int[] current, int[] previous) {
        int[] first = new int[]{maze[current[0]][current[1]][0][0], maze[current[0]][current[1]][0][1]};
        int[] second = new int[]{maze[current[0]][current[1]][1][0], maze[current[0]][current[1]][1][1]};

        if (first[0] == previous[0] && first[1] == previous[1])
            return second;
        else return first;
    }
}
