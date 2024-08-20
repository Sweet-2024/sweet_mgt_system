package main_entities;

public class Messaging {
    private String senderEmail;
    private String receiverEmail;
    private String msg;
    public Messaging(String senderEmail, String receiverEmail, String msg) {
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.msg = msg;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
