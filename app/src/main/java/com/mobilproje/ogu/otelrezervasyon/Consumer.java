package com.mobilproje.ogu.otelrezervasyon;

import java.util.UUID;

public class Consumer {
    private long consumerID;
    private String consumerName;
    private String consumerSurname;
    private String consumerPassword;
    private String consumerPhone;
    private String consumerMail;

    public Consumer(long consumerID, String consumerName, String consumerSurname, String consumerPassword, String consumerPhone, String consumerMail) {
        this.consumerID = consumerID;
        this.consumerName = consumerName;
        this.consumerSurname = consumerSurname;
        this.consumerPassword = consumerPassword;
        this.consumerPhone = consumerPhone;
        this.consumerMail = consumerMail;
    }

    public Consumer() {
    }

    public long getConsumerID() {
        return consumerID;
    }

    public void setConsumerID(long consumerID) {
        this.consumerID = consumerID;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerSurname() {
        return consumerSurname;
    }

    public void setConsumerSurname(String consumerSurname) {
        this.consumerSurname = consumerSurname;
    }

    public String getConsumerPassword() {
        return consumerPassword;
    }

    public void setConsumerPassword(String consumerPassword) {
        this.consumerPassword = consumerPassword;
    }

    public String getConsumerPhone() {
        return consumerPhone;
    }

    public void setConsumerPhone(String consumerPhone) {
        this.consumerPhone = consumerPhone;
    }

    public String getConsumerMail() {
        return consumerMail;
    }

    public void setConsumerMail(String consumerMail) {
        this.consumerMail = consumerMail;
    }
}
