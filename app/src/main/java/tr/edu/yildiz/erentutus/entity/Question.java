package tr.edu.yildiz.erentutus.entity;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> choices;
    private String answer;
    public static ArrayList<Question> questions = new ArrayList<Question>();

    public Question(String question, ArrayList<String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public static ArrayList<Question> getQuestions() {
        return questions;
    }

    public static void setQuestions(ArrayList<Question> questions) {
        Question.questions = questions;
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
