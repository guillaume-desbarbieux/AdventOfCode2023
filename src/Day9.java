import java.util.List;

public class Day9 {
    public static void main(String[] args) {
        part1();
    }

    public static void part1() {
        List<String> lines = Utils.readFile("ressources/mirageMaintenance.txt");
        long totalSum = 0;
        for (String line : lines) {
            List<Long> history = Utils.getLongsFromString(line);
            while(!isAllZero(history)){
                totalSum += history.get(history.size()-1);
                getListDifferences(history);
            }
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