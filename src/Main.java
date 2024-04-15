import FileService.FileReaderService;
import FileService.TextToCharacterProportionMapParserService;
import Model.*;


import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String trainingData = "./TrainingData";

        List<Language> languageList = new ArrayList<>();

        //Wczytanie danych testowych

        for (String directoryName : FileReaderService.getListOfSubdirectories(trainingData)) {

            List<Map<Character, Double>> listOfMapOfProportions = new ArrayList<>();

            for (String fileName : FileReaderService.getListOfFilesInDirectory(trainingData +
                    System.getProperty("file.separator") + directoryName)) {

                String text = FileReaderService.readFile(trainingData + System.getProperty("file.separator") +
                        directoryName + System.getProperty("file.separator") + fileName);


                Map<Character, Double> proportionsMap =
                        TextToCharacterProportionMapParserService.parseTextToCharacterProportionMap(text);

                //Debug:
                /*
                System.out.println(text);
                System.out.println("---------------------------------------------------");
                System.out.println(proportionsMap);
                System.out.println("---------------------------------------------------");
                 */
                listOfMapOfProportions.add(proportionsMap);
            }

            languageList.add(new Language(directoryName, listOfMapOfProportions));
        }

        //Trenowanie Warstwy Perceptronów d

        List<Perceptron> perceptronLayer = new ArrayList<>();


        for (Language language : languageList) {
            //TODO:
            //add perceptron for each language
            //perceptronLayer.add(new Perceptron());
        }




       /*

        for (Language language : languageList)
            perceptronLayer.add(new Perceptron(language.getCharacter().get(0).size()-2,0.05,language.getName()));

        List<Node> trainList = new ArrayList<>();

        for (Language language : languageList) {
            for (Map<Character,Long> singleMap:language.getCharacter()) {
                List<Double> attributesColumn = new ArrayList<>();
                for (int i = 'a'; i <= 'z'; i++)
                    attributesColumn.add((singleMap.get((char) i) / (double)((singleMap.get('@') - singleMap.get('!')))));
                trainList.add(new Node(attributesColumn,language.getName()));
            }
        }

        for (int i = 0; i < languageList.size() * 10000; i++) {
            Collections.shuffle(trainList);
            for (Node node : trainList)
                for (Perceptron perceptron : perceptronLayer)
                    perceptron.learn(node, (node.getNodeClassName().equals(perceptron.getTrainedForLanguageName()) ? 1 : 0));
        }

        for (Perceptron perceptron : perceptronLayer)
            perceptron.normalizePerceptron();

        SwingUtilities.invokeLater(() -> new PerceptronLayerView(perceptronLayer));

        /*
        */
    }
}
