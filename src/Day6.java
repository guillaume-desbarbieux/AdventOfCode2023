import java.util.List;

public class Day6 {

    public static void main(String[] args) {
        Race race1 = new Race(40, 215);
        Race race2 = new Race(92, 1064);
        Race race3 = new Race(97, 1505);
        Race race4 = new Race(90, 1100);
        long margin = race1.chancesToWin * race2.chancesToWin * race3.chancesToWin * race4.chancesToWin;
        System.out.println(margin);

        Race race5 = new Race(40929790, 215106415051100L);
        System.out.println(race5.chancesToWin);

    }

    public static class Race {
        long time;
        long record;
        long chancesToWin;

        public Race(long time, long record) {
            this.time = time;
            this.record = record;
            this.chancesToWin = getChancesToWin();
        }

        public long getChancesToWin() {
            long counter = 0;
            for (int t = 1; t < time; t++) {
                if (record < t * (time - t))
                   counter++;
            }
            return counter;
        }
    }

    public class Day7 {

        public static void main(String[] args) {
            List<String> lines = Utils.readFile("ressources/camelCards.txt");
            for (String line : lines) {
                System.out.println(line);
                }
            }
        }
}
