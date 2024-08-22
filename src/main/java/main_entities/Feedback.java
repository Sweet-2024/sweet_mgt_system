package main_entities;

public class Feedback
{
    int productID;
    int evaluation;

    public Feedback(int productID, int evaluation) {
        this.productID = productID;
        this.evaluation = evaluation;
    }

    public int getProductID() {
        return productID;
    }

    public int getEvaluation() {
        return evaluation;
    }
}
