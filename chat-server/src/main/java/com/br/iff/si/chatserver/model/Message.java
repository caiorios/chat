package com.br.iff.si.chatserver.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {
    
    private String author;
    private String recipient;
    private String content;

    public Message() {
    }

    public Message(String author, String recipient, String content) {
        this.author = author;
        this.recipient = recipient;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
