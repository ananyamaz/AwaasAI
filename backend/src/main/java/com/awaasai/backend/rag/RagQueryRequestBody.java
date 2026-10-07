package com.awaasai.backend.rag;

public class RagQueryRequestBody {

    private String question;

    public RagQueryRequestBody() {
    }

    public RagQueryRequestBody(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}