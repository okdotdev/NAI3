import FileService.FileReaderService;
import FileService.TextToCharacterProportionMapParserService;
import Model.*;
import View.PerceptronLayerView;


import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String trainingData = "./TrainingData";

        List<Language> languageList = new ArrayList<>();

        //Wczytanie danych treningowych i stworzenie listy języków

        for (String directoryName : FileReaderService.getListOfSubdirectories(trainingData)) {

            List<Observation> observations = new ArrayList<>();

            for (String fileName : FileReaderService.getListOfFilesInDirectory(trainingData +
                    System.getProperty("file.separator") + directoryName)) {

                String text = FileReaderService.readFile(trainingData + System.getProperty("file.separator") +
                        directoryName + System.getProperty("file.separator") + fileName);

                Map<Character, Double> proportionsMap =
                        TextToCharacterProportionMapParserService.parseTextToCharacterProportionMap(text);

                Observation observation = new Observation(proportionsMap, directoryName);

                observations.add(observation);
            }

            languageList.add(new Language(directoryName, observations));
        }


        //Trenowanie Warstwy Perceptronów

        PerceptronLayer perceptronLayer = new PerceptronLayer();
        perceptronLayer.trainLayerForLanguages(languageList);

        //Testowanie
        String testData = "./TestData";
        int correctPredictions = 0;
        int totalPredictions = 0;

        for (String directoryName : FileReaderService.getListOfSubdirectories(testData)) {

            for (String fileName : FileReaderService.getListOfFilesInDirectory(testData +
                    System.getProperty("file.separator") + directoryName)) {

                String text = FileReaderService.readFile(testData + System.getProperty("file.separator") +
                        directoryName + System.getProperty("file.separator") + fileName);

                Map<Character, Double> proportionsMap =
                        TextToCharacterProportionMapParserService.parseTextToCharacterProportionMap(text);

                Observation observation = new Observation(proportionsMap, directoryName);

                String languageName = perceptronLayer.predicateLanguage(observation);


                System.out.println("Predicted: " + languageName + " In File: " + directoryName + "/" + fileName);
                if (languageName.equals(directoryName)) {
                    correctPredictions++;
                    System.out.println("Correct prediction");
                }

                totalPredictions++;
            }
        }

        System.out.println("Correct predictions: " + correctPredictions + " out of " + totalPredictions);
        double percentage = (double) correctPredictions / totalPredictions * 100;
        System.out.println("Correct predictions percentage: " + percentage + "%");

        new PerceptronLayerView(perceptronLayer);

    }
}
