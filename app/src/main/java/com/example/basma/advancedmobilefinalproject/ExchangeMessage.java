package com.example.basma.advancedmobilefinalproject;
import java.util.*;

public class ExchangeMessage {

    private String messageText;
    private String messageUser;
    private long messageTime;

    public ExchangeMessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        messageTime = new Date().getTime();
    }

    public ExchangeMessage( String messageUser){

        this.messageText = "I would like to exchange your book";
        this.messageUser = messageUser;
        messageTime = new Date().getTime();

    }


    public ExchangeMessage(){


    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}