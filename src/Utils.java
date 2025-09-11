import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> readFile(String path)  {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


    public static List<Long> getNumbersFromString(String string) {
        if (string.isEmpty()) return new ArrayList<>();
        List<Long> numbers = new ArrayList<>();
        long number = 0;
        boolean foundNumber = false;
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c)) {
                foundNumber = true;
                number = number * 10 + Character.getNumericValue(c);
            } else {
                if (foundNumber){
                    numbers.add(number);
                    foundNumber = false;
                    number = 0;
                }
            }
        }
        if (foundNumber) numbers.add(number);
        return numbers;
    }

    public static int getIntFromString(String string) {
        if (string.isEmpty()) return 0;
        return getNumbersFromString(string).get(0).intValue();
    }

    public static List<Integer> getDigitsFromString(String string) {
        List<Integer> digits = new ArrayList<>();
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c))
                digits.add(Character.getNumericValue(c));
        }
        return digits;
    }

    public static String replaceUsingDict(String string, String[] dictSource, String[] dictTarget) {
        char[] input = string.toCharArray();
        StringBuilder result = new StringBuilder();

        // On parcourt l'input
        for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
            boolean found = false;

            // On parcourt le dictionnaire
            for (int dictIndex = 0; dictIndex < dictSource.length && !found; dictIndex++) {
                char[] word = dictSource[dictIndex].toCharArray();

                //On vérifie que le mot du dictionnaire n'est pas trop long
                if (inputIndex + word.length <= string.length()) {

                    //On parcourt les caractères du mot du dictionnaire
                    boolean corresponding = true;
                    for (int wordIndex = 0; wordIndex < word.length && corresponding; wordIndex++) {

                        // On vérifie la correspondance du mot du dictionnaire à la chaine de caractères
                        if (input[inputIndex + wordIndex] != word[wordIndex])
                            corresponding = false;
                    }
                    // Si correspondance, on ajoute le mot du dictTarget au résultat
                    if (corresponding) {
                        result.append(dictTarget[dictIndex]);
                        found = true;
                    }
                }
            }

            if (!found) result.append(input[inputIndex]);
        }
        return result.toString();
    }

    public static boolean compareStrings(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) return false;
        }
        return true;
    }

    public static boolean contains(List<Integer> list, int number){
        for (int i : list) {
            if (i == number) return true;
        }
        return false;
    }

    public static boolean contains(String string, char character){
        for (char c : string.toCharArray()) {
            if (c == character) return true;
        }
        return false;
    }

    public static List<String> split(String string, char separator){
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (c == separator) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());
        return result;
    }
}
