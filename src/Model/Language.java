package Model;


import java.util.List;
import java.util.Map;

public class Language {

    String name;
    List<Map<Character, Double>> characterProportionMapList;

    public Language(String name, List<Map<Character, Double>> mapList) {
        this.name = name;
        this.characterProportionMapList = mapList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<Character,Double>> getCharacterProportionMapList() {
        return characterProportionMapList;
    }
}