import FileService.FileReaderService;
import FileService.TextToCharacterProportionMapParserService;
import Model.*;


import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String trainingData = "./TrainingData";

        List<Language> languageList = new ArrayList<>();

        //Wczytanie danych treningowych i stworzenie listy języków

        for (String directoryName : FileReaderService.getListOfSubdirectories(trainingData)) {

            List<Map<Character, Double>> observations = new ArrayList<>();

            for (String fileName : FileReaderService.getListOfFilesInDirectory(trainingData +
                    System.getProperty("file.separator") + directoryName)) {

                String text = FileReaderService.readFile(trainingData + System.getProperty("file.separator") +
                        directoryName + System.getProperty("file.separator") + fileName);

                Map<Character, Double> proportionsMap =
                        TextToCharacterProportionMapParserService.parseTextToCharacterProportionMap(text);

                observations.add(proportionsMap);
            }

            languageList.add(new Language(directoryName, observations));
        }


        //Trenowanie Warstwy Perceptronów

        List<Perceptron> perceptronLayer = new ArrayList<>();


        for (Language language : languageList) {
            //TODO:
            //add perceptron for each language
            //perceptronLayer.add(new Perceptron());
            //trainperceptron
        }

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

                String languageName = "";
                for (Perceptron p : perceptronLayer) {
                    if (p.isTrainedFroTheLanguage(proportionsMap)) {
                        languageName = p.getTrainedForLanguageName();
                        System.out.println("Predicted: " + languageName + " Actual: " + directoryName);
                        break;
                    }
                }

                if (languageName.equals(directoryName)) {
                    correctPredictions++;
                    System.out.println("Correct prediction");
                }
                totalPredictions++;
            }
        }

        //print accuracy

        System.out.println("Accuracy: " + (double) correctPredictions / totalPredictions);


    }
}
