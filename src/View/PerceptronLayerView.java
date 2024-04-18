package View;

import FileService.TextToCharacterProportionMapParserService;
import Model.Observation;
import Model.PerceptronLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerceptronLayerView extends JFrame {

    private final PerceptronLayer perceptronLayer;
    private final JTextField inputField1;
    private final JLabel predictionLabel;

    public PerceptronLayerView(PerceptronLayer perceptronLayer) throws HeadlessException {
        this.perceptronLayer = perceptronLayer;

        setTitle("Perceptron Layer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        JLabel inputLabel1 = new JLabel("Input :");
        inputField1 = new JTextField();
        JButton predictButton = new JButton("Predict");
        predictionLabel = new JLabel("Prediction: ");

        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Observation observation = new Observation(TextToCharacterProportionMapParserService
                            .parseTextToCharacterProportionMap(inputField1.getText()), "USER");
                    String prediction = perceptronLayer.predicateLanguage(observation);
                    predictionLabel.setText("Prediction: " + prediction);
                } catch (NumberFormatException ex) {
                    predictionLabel.setText("Prediction: Invalid input");
                }
            }
        });

        mainPanel.add(inputLabel1);
        mainPanel.add(inputField1);
        mainPanel.add(predictButton);
        mainPanel.add(predictionLabel);

        add(mainPanel);
        setVisible(true);
    }

}