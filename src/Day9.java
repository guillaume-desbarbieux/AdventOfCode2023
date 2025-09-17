import java.util.List;

public class Day9 {
    public static void main(String[] args) {
        part1();
    }

    public static void part1() {
        List<String> lines = Utils.readFile("ressources/mirageMaintenance.txt");
        long sumLastPredictions = 0;
        long sumFirstPredictions = 0;
        for (String line : lines) {
            long lastPredictions = 0;
            long firstPredictions = 0;
            List<Long> history = Utils.getLongsFromString(line);

            while(!isAllZero(history)){
                lastPredictions += history.get(history.size()-1);
                firstPredictions = history.get(0) - firstPredictions;
                getListDifferences(history);
            }
            sumLastPredictions += lastPredictions;
            sumFirstPredictions += firstPredictions;
        }
        System.out.println("Sum Last: " + sumLastPredictions);
        System.out.println("Sum First: " + sumFirstPredictions);
    }

    private static boolean isAllZero(List<Long> list) {
        for (Long i : list){
            if (i != 0) return false;
        }
        return true;
    }

    public static void getListDifferences (List<Long> list){
        for (int i = 0 ; i < list.size() - 1 ; i++){
            list.set(i,list.get(i+1)-list.get(i));
        }
        list.remove(list.size()-1);
    }
}