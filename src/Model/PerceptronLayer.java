package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PerceptronLayer {
    private final List<Perceptron> perceptronLayer;

    public PerceptronLayer() {
        perceptronLayer = new ArrayList<>();
    }

    public void trainLayerForLanguages(List<Language> languageList) {
        for (Language language : languageList) {
            String name = language.getName();
            Perceptron p = new Perceptron(language.getLanguageObservations().get(0).size(), 0.1, name);
            p.trainPerceptronForLanguage(language.getLanguageObservations());
            perceptronLayer.add(p);
        }
    }


    public String predicateLanguage(Map<Character, Double> proportionsMap) {
        double max = 0;
        String languageName = "";
        for (Perceptron p : perceptronLayer) {
            double result = p.calculateOutput(proportionsMap);
            if (result > max) {
                max = result;
                languageName = p.getTrainedForLanguageName();
            }
        }
        return languageName;
    }

}
