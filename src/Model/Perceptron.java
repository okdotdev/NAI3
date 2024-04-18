package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Perceptron {

    private final String trainedForLanguageName;
    private final Map<Character, Double> weightsMap; //vector w
    private double threshold; //theta
    private final double bias; // b
    private final double learningRate; //alpha
    private int epoch = 0;

    public Perceptron(String trainedForLanguage) {
        this.trainedForLanguageName = trainedForLanguage;
        this.learningRate = 0.1;
        this.weightsMap = new HashMap<>();
        int vectorSize = 26; // 26 liter w alfabecie

        for (int i = 0; i < vectorSize; i++) {
            //weightsMap.put((char) ('a' + i), 0.1 + Math.random());
            weightsMap.put((char) ('a' + i), 1.0);
        }

        //threshold = 0.1 + Math.random();
        threshold = 0.0;
        bias = 0.1;

    }

    public void trainPerceptronForLanguage(List<Observation> trainingData) {
        do {
            trainingEpoch(trainingData);
            epoch++;


        } while (!isPerceptronTrainedForObservationsOfLanguage(trainingData));

        System.out.println("Weights for " + trainedForLanguageName + " language: " + weightsMap);
        System.out.println("Threshold for " + trainedForLanguageName + " language: " + threshold);
        System.out.println("Epochs for " + trainedForLanguageName + " language: " + epoch);
    }


    private void trainingEpoch(List<Observation> observations) {
        for (Observation observation : observations) {
            if (!hasCorrectPrediction(observation)) {
                updateWeights(observation);
            }

        }
    }

    private void updateWeights(Observation observation) {
        //theta' = theta + (y-d)*b
        //w' = w + (d-y)*alpha *x

        int d = observation.getOrigin().equals(trainedForLanguageName) ? 1 : 0;
        int y = predicateLanguage(observation.getProportionsMap()) ? 1 : 0;

        for (Character character : observation.getProportionsMap().keySet()) {
            double newWeight = weightsMap.get(character);
            newWeight += (d - y) * learningRate * observation.getProportionsMap().get(character);
            weightsMap.put(character, newWeight);

        }
        threshold = threshold + (y - d) * bias;

    }

    private boolean isPerceptronTrainedForObservationsOfLanguage(List<Observation> observations) {
        for (Observation observation : observations) {
            if (!hasCorrectPrediction(observation)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCorrectPrediction(Observation observation) {
        boolean isThisLanguagePerceptronWasTrainedFor = predicateLanguage(observation.getProportionsMap());

        if (isThisLanguagePerceptronWasTrainedFor && observation.getOrigin().equals(trainedForLanguageName)) {
            return true;
        }


        if (isThisLanguagePerceptronWasTrainedFor && !observation.getOrigin().equals(trainedForLanguageName)) {
            return false;
        }


        if (!isThisLanguagePerceptronWasTrainedFor && observation.getOrigin().equals(trainedForLanguageName)) {
            return false;
        }

        return !isThisLanguagePerceptronWasTrainedFor && !observation.getOrigin().equals(trainedForLanguageName);
    }

    public boolean predicateLanguage(Map<Character, Double> proportionsMap) {
        double netValue = getNetValue(proportionsMap);
        //  System.out.println("Net value for " + trainedForLanguageName + " language: " + netValue + " Threshold: " + threshold);
        return netValue >= threshold;
    }


    public double getNetValue(Map<Character, Double> proportionsMap) {
        double netValue = 0.0;
        for (Character character : proportionsMap.keySet()) {
            netValue += proportionsMap.get(character) * weightsMap.get(character);
        }
        //  System.out.println("Net value for " + trainedForLanguageName + " language: " + netValue);
        return netValue;
    }


    public String getTrainedForLanguageName() {
        return trainedForLanguageName;
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "lang='" + trainedForLanguageName + '}';
    }
}