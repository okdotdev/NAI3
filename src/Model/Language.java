package Model;


import java.util.List;
import java.util.Map;

public class Language {

    String name;
    List<Map<Character, Double>> observations;

    public Language(String name, List<Map<Character, Double>> mapList) {
        this.name = name;
        this.observations = mapList;
    }


    public String getName() {
        return name;
    }

    public List<Map<Character,Double>> getLanguageObservations() {
        return observations;
    }
}