import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character, Integer> s = new TreeMap<>();
        for (char i = 'a'; i <='z'; i++) {
            s.put(i, i - 'a' + 1);
        }
        return s;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer, Integer> s = new TreeMap<>();
        for (int i : nums) {
            s.put(i, i * i);
        }
        return s;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String, Integer> s = new TreeMap<>();
        for (String word : words) {
            s.put(word, s.getOrDefault(word, 0) + 1);
        }//put 方法在 Map（如 TreeMap 或 HashMap）中，
        // 如果对同一个 key 再次调用，会用新的 value 覆盖原有的值
        //getOrDefault
        return s;
    }

}
