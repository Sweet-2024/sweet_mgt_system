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

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }
}
