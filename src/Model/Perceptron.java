package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Perceptron {

    private final String trainedForLanguageName;
    private final Map<Character, Double> weightsMap;
    private final double thetaThreshold;
    private final double alpha;

    public Perceptron(int vectorSize, double alpha, String trainedForLanguage) {
        this.trainedForLanguageName = trainedForLanguage;
        this.alpha = alpha;
        this.weightsMap = new HashMap<>();

        for (int i = 0; i < vectorSize; i++) {
            weightsMap.put((char) ('a' + i), 0.0);
        }

        this.thetaThreshold = 1.0;
    }

    public void trainPerceptronForLanguage(List<Map<Character, Double>> observations) {
        while (!isPerceptronTrainedForObservationsOfLanguage(observations)) {
            trainingEpoch(observations);
        }
    }

    private boolean isPerceptronTrainedForObservationsOfLanguage(List<Map<Character, Double>> observations) {
        for (Map<Character, Double> observation : observations) {
            if (!hasCorrectPrediction(observation)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCorrectPrediction(Map<Character, Double> observation) {
        double output = calculateOutput(observation);
        double expectedOutput = observation.get('a') == 1.0 ? 1.0 : 0.0;
        return output == expectedOutput;
    }


    private void trainingEpoch(List<Map<Character, Double>> observations) {
        for (Map<Character, Double> observation : observations) {
            double output = calculateOutput(observation);
            double expectedOutput = observation.get('a') == 1.0 ? 1.0 : 0.0;
            updateWeights(observation, expectedOutput, output);
        }
    }


    private void updateWeights(Map<Character, Double> observation, double expectedOutput, double output) {
        for (Character character : observation.keySet()) {

            if (!weightsMap.containsKey(character) || !observation.containsKey(character)) {
                continue;
            }

            double newWeight = weightsMap.get(character) + alpha * (expectedOutput - output) * observation.get(character);
            weightsMap.put(character, newWeight);
        }
    }


    public double calculateOutput(Map<Character, Double> proportionsMap) {
        double netValue = 0;
        for (Character character : proportionsMap.keySet()) {
            if (!weightsMap.containsKey(character)) {
                continue;
            }
            netValue += proportionsMap.get(character) * weightsMap.get(character);

        }
        return netValue > thetaThreshold ? 1.0 : 0.0;
    }


    public String getTrainedForLanguageName() {
        return trainedForLanguageName;
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "weightsMap=" + weightsMap +
                ", thetaThreshold=" + thetaThreshold +
                '}';
    }


}