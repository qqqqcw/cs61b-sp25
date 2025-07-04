import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int a = 0;
        for(int i : L) {
            a += i;
        }
        return a;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> a = new ArrayList<>();
        for(int i : L) {
            if(i % 2 == 0) {
                a.add(i);
            }
        }
        return a;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> a = new ArrayList<>();
        int i1 = L1.size();
        int i2 = L2.size();
        for (int i = 0; i < i1; i++) {
            for (int j = 0; j < i2; j++) {
                if (L1.get(i).equals(L2.get(j))) {
                    a.add(L1.get(i));
                    break;
                }
            }
        }
        return a;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int cnt = 0;
        for (String i : words) {
            for(int j = 0; j < i.length(); j++) {
                        if (c == i.charAt(j)) {
                            cnt++;
                        }
            }
        }
        return cnt;
    }
}
