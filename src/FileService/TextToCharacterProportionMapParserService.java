package FileService;

import java.util.HashMap;
import java.util.Map;

public class TextToCharacterProportionMapParserService {

    public static Map<Character, Double> parseTextToCharacterProportionMap(String text) {
        Map<Character, Double> map = new HashMap<>();
        Map<Character, Integer> countMap = getCharacterCountMap(text);
        int totalCharacterCount = getTotalCharacterCount(countMap);

        for (char c : countMap.keySet()) {
            map.put(c, countMap.get(c) / (double) totalCharacterCount);
        }

        return map;
    }

    private static Map<Character, Integer> getCharacterCountMap(String text) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : text.toCharArray()) {
            if (c < 'a' || c > 'z') {
                continue;
            }
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }

    private static int getTotalCharacterCount(Map<Character, Integer> map) {
        int total = 0;
        for (int count : map.values()) {
            total += count;
        }
        return total;
    }

}
