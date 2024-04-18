package Model;

import java.util.Map;

public class Observation {
    private final Map<Character, Double> proportionsMap;
    private final String origin;


    public Observation(Map<Character, Double> proportionsMap, String origin) {
        this.proportionsMap = proportionsMap;
        this.origin = origin;

    }



    public Map<Character, Double> getProportionsMap() {
        return proportionsMap;
    }

    public String getOrigin() {
        return origin;
    }



    @Override
    public String toString() {
        return "Observation{" +
                ", origin='" + origin + '\'' +
                '}';
    }
}
