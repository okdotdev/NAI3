package Model;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {
    String trainedForLanguageName;
   private List<Double> weightsVector;
   private double thetaThreshold;
   private double alpha;

    public String getTrainedForLanguageName() {
        return trainedForLanguageName;
    }

    public Perceptron(int vectorSize, double alpha, String trainedForLanguage) {
        this.trainedForLanguageName =trainedForLanguage;
        this.alpha = alpha;
        this.weightsVector = new ArrayList<>();
        for (int i = 0; i < vectorSize ; i++) {
            this.weightsVector.add(1.0);
            //this.weightsVector.add(Math.random()*2-1);
        }
        this.thetaThreshold = 1.0;
        //this.thetaThreshold = Math.random()*2-1;
    }

    public void learn(Node node, int correctAnswer){
        double scalarProduct = 0;
        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Calculate X * W
            scalarProduct += node.getAttributesColumn().get(i) * this.weightsVector.get(i);

        int y = (scalarProduct>=this.thetaThreshold?1:0);

        if (y != correctAnswer) { //Do learn
            //System.out.println("LEARN - " + trainedForLanguageName);
            List<Double> vectorWPrime = new ArrayList<>(this.weightsVector);
            for (int i = 0; i < node.getAttributesColumn().size(); i++) // W' = W + (Correct-Y) * Alpha * X
                vectorWPrime.set(i, (this.weightsVector.get(i) + ((correctAnswer - y) * alpha * node.getAttributesColumn().get(i))));

            this.weightsVector = vectorWPrime;
            this.thetaThreshold = thetaThreshold + (correctAnswer - y) * alpha * -1;
        }
    }

    public double evaluate(Node node){
        double scalarProduct = 0;
        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Calculate X * W
            scalarProduct += node.getAttributesColumn().get(i) * this.weightsVector.get(i);

        return scalarProduct - thetaThreshold; //net
    }

    public void normalizePerceptron(){
        double sumOfSquares=0;

        for (double d : this.weightsVector)
            sumOfSquares+=Math.pow(d,2);

        double length = Math.sqrt(sumOfSquares);

        List<Double>  newVectorW = new ArrayList<>();

        for (int i = 0; i < this.weightsVector.size() ; i++)
            newVectorW.add(i,(this.weightsVector.get(i)/length));

        this.thetaThreshold = thetaThreshold/length;
        this.weightsVector = newVectorW;
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "weightsVector=" + weightsVector +
                ", thetaThreshold=" + thetaThreshold +
                '}';
    }
}