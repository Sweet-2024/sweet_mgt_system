package Entities;

public class Feedback
{
    int orderID;
    int evaluation;

    public Feedback(int orderID, int evaluation) {
        this.orderID = orderID;
        this.evaluation = evaluation;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }
}
