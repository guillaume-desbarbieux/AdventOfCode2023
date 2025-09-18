import java.util.List;

public class Day10 {
    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/PipeMaze.txt");
        System.out.println(part1(lines));
    }

    public static int part1(List<String> lines) {
        int hauteur = lines.size();
        int largeur = lines.get(0).length();
        int[][][][] maze = new int[hauteur][largeur][2][2];
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
        int codeForSChar = 0;
        if (iStart > 0) {
            char c = lines.get(iStart - 1).charAt(jStart);
            if (c == '|' || c == '7' || c == 'F') {
                maze[iStart][jStart][foundNeigbours] = new int[]{iStart - 1, jStart};
                foundNeigbours++;
                codeForSChar++;
            }
        }
        if (iStart < hauteur - 1) {
            char c = lines.get(iStart + 1).charAt(jStart);
            if (c == '|' || c == 'L' || c == 'J') {
                maze[iStart][jStart][foundNeigbours] = new int[]{iStart + 1, jStart};
                foundNeigbours++;
                codeForSChar += 4;
            }
        }
        if (jStart > 0) {
            char c = lines.get(iStart).charAt(jStart - 1);
            if (c == '-' || c == 'L' || c == 'F') {
                maze[iStart][jStart][foundNeigbours] = new int[]{iStart, jStart - 1};
                foundNeigbours++;
                codeForSChar += 8;
            }
        }
        if (jStart < largeur - 1) {
            char c = lines.get(iStart).charAt(jStart + 1);
            if (c == '-' || c == 'J' || c == '7') {
                maze[iStart][jStart][foundNeigbours] = new int[]{iStart, jStart + 1};
                codeForSChar += 2;
            }
        }


        int counter = 0;
        int[] current = new int[]{iStart, jStart};
        int[] previous = current;
        int[] next;

        int[][] clearedMaze = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                clearedMaze[i][j] = 0;
            }
        }

        switch (codeForSChar) {
            case 5 -> clearedMaze[iStart][jStart] = 1;
            case 3, 12 -> clearedMaze[iStart][jStart] = 2;
            case 6, 9 -> clearedMaze[iStart][jStart] = 3;
        }

        do {
            next = getNext(maze, current, previous);
            previous = current;
            current = next;
            switch (lines.get(current[0]).charAt(current[1])) {
                case '|' -> clearedMaze[current[0]][current[1]] = 1;
                case 'L', '7' -> clearedMaze[current[0]][current[1]] = 2;
                case 'F', 'J' -> clearedMaze[current[0]][current[1]] = 3;
                case '-' -> clearedMaze[current[0]][current[1]] = -1;
            }
            counter++;
        } while (current[0] != iStart || current[1] != jStart);

        System.out.println(getInsideTiles(clearedMaze));


        return counter / 2;
    }

    private static int getInsideTiles(int[][] maze) {
        int counter = 0;
        int lastCorner = 0;
        for (int[] line : maze) {
            boolean inside = false;
            for (int pipe : line) {
                switch (pipe) {
                    case 0:
                        System.out.print(" ");
                        if (inside)
                            counter++;
                        break;
                    case 1:
                        System.out.print("|");
                        inside = !inside;
                        break;
                    case 2:
                        System.out.print("\\");
                        if (lastCorner == 2) {
                            inside = !inside;
                            lastCorner = 0;
                        } else
                            lastCorner = 2;
                        break;
                    case 3:
                        System.out.print("/");
                        if (lastCorner == 3) {
                            inside = !inside;
                            lastCorner = 0;
                        } else
                            lastCorner = 3;
                        break;
                    case -1:
                        System.out.print("-");
                        break;
                }

            }
            System.out.println();
        }
        return counter;
    }

    private static int[] getNext(int[][][][] maze, int[] current, int[] previous) {
        int[] first = new int[]{maze[current[0]][current[1]][0][0], maze[current[0]][current[1]][0][1]};
        int[] second = new int[]{maze[current[0]][current[1]][1][0], maze[current[0]][current[1]][1][1]};

        if (first[0] == previous[0] && first[1] == previous[1])
            return second;
        else return first;
    }
}