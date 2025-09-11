public class Day6 {

    public static void main(String[] args) {
        Race race1 = new Race(40, 215);
        Race race2 = new Race(92, 1064);
        Race race3 = new Race(97, 1505);
        Race race4 = new Race(90, 1100);
        int margin = race1.chancesToWin * race2.chancesToWin * race3.chancesToWin * race4.chancesToWin;
        System.out.println(margin);

    }

    public static class Race {
        int time;
        int record;
        int chancesToWin;

        public Race(int time, int record) {
            this.time = time;
            this.record = record;
            this.chancesToWin = getChancesToWin();
        }

        public int getChancesToWin() {
            int counter = 0;
            for (int t = 1; t < time; t++) {
                if (record < t * (time - t))
                   counter++;
            }
            return counter;
        }

    }
}
