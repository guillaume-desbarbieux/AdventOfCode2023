import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public static void main(String[] args) {
        String path = "ressources/mirageMaintenance.txt";
        part1(path);
    }
    public static void part1(String path) {
        //System.out.println("test for {} " + isAllZero(new ArrayList<>()));
        List<String> lines = Utils.readFile(path);
        long totalSum = 0;
        for (String line : lines) {
            List<Long> history = Utils.getLongsFromString(line);

            long prediction = 0;
            while(!isAllZero(history) && history.size() > 1){
                //System.out.println(history);
                prediction += history.get(history.size()-1);
                getListDifferences(history);
            }
            //System.out.println(history);
            //System.out.println("Prediction : " + prediction);
            totalSum += prediction;
        }
        System.out.println("Sum : " + totalSum);
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