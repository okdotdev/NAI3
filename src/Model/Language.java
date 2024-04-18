package Model;


import java.util.List;
import java.util.Map;

public class Language {

    String name;
    List<Observation> observations;

    public Language(String name, List<Observation> mapList) {
        this.name = name;
        this.observations = mapList;
    }


    public String getName() {
        return name;
    }

    public List<Observation> getLanguageObservations() {
        return observations;
    }


}