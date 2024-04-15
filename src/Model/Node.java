package Model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Double> attributesColumn; //attributes columns
    private final String nodeClassName; //decision column


    public Node(List<Double> attributesColumn, String irisClassName) {
        this.attributesColumn = attributesColumn;
        this.nodeClassName = irisClassName;
    }

    public void normalizeNode(){
        double sumOfSquares=0;

        for (double d : this.attributesColumn)
            sumOfSquares+=Math.pow(d,2);

        double length = sumOfSquares;
        length = Math.sqrt(length);

        List<Double>  newAttributesColumn = new ArrayList<>();

        for (int i = 0; i < this.attributesColumn.size() ; i++)
            newAttributesColumn.add(i,(this.attributesColumn.get(i)/length));

        this.attributesColumn = newAttributesColumn;
    }

    public List<Double> getAttributesColumn() {
        return attributesColumn;
    }

    public String getNodeClassName() {
        return nodeClassName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "attributesColumn=" + attributesColumn +
                ", irisClassName='" + nodeClassName + '\'' +
                '}';
    }
}