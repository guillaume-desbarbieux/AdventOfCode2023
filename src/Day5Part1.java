import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5Part1 {

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("ressources/seed.txt");

        List<Long> seeds = Utils.getNumbersFromString("seeds: 364807853 408612163 302918330 20208251 1499552892 200291842 3284226943 16030044 2593569946 345762334 3692780593 17215731 1207118682 189983080 2231594291 72205975 3817565407 443061598 2313976854 203929368");
        System.out.println(seeds);
    }








}