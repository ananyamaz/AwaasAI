package com.awaasai.backend.rag;

public class RagQueryRequest {

    private String question;

    public RagQueryRequest() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}