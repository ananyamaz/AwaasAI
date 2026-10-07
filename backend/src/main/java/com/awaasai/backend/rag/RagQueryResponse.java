package com.awaasai.backend.rag;

import java.util.List;
import java.util.Map;

public class RagQueryResponse {

    private String question;
    private String answer;
    private List<Map<String, Object>> sources;

    public RagQueryResponse() {
    }

    public RagQueryResponse(
            String question,
            String answer,
            List<Map<String, Object>> sources) {
        this.question = question;
        this.answer = answer;
        this.sources = sources;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Map<String, Object>> getSources() {
        return sources;
    }

    public void setSources(List<Map<String, Object>> sources) {
        this.sources = sources;
    }
}