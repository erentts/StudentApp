package tr.edu.yildiz.erentutus.entity;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> choices;
    private String answer;

    public Question(String question, ArrayList<String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    public String getContent() {
        return question;
    }

    public void setContent(String content) {
        this.question = content;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
