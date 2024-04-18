package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerceptronLayer {
    private final List<Perceptron> perceptronLayer;

    public PerceptronLayer() {
        perceptronLayer = new ArrayList<>();
    }

    public void trainLayerForLanguages(List<Language> languageList) {

        List<Observation> trainingData = new ArrayList<>();
        for (Language language : languageList) {
            trainingData.addAll(language.getLanguageObservations());
        }

        Collections.shuffle(trainingData);

        //print training data



        for (Language language : languageList) {
            String name = language.getName();
            Perceptron p = new Perceptron(name);
            p.trainPerceptronForLanguage(trainingData);
            //  p.trainPerceptronForLanguage(language.getLanguageObservations());
            perceptronLayer.add(p);
        }
    }


    public String predicateLanguage(Observation observation) {
        String languageName = "NOT_PREDICTED";
        for (Perceptron p : perceptronLayer) {
            if (p.predicateLanguage(observation.getProportionsMap())) {
                languageName = p.getTrainedForLanguageName();
            }
        }
        return languageName;
    }

}
