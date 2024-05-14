package domain;

public class Question {
    private int id;
    private String text;
    private String corrAnswer;
    private int score;
    private String userAnswer;

    public Question(int id, String text, String corrAnswer, int score, String userAnswer) {
        this.id = id;
        this.text = text;
        this.corrAnswer = corrAnswer;
        this.score = score;
        this.userAnswer = userAnswer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCorrAnswer() {
        return corrAnswer;
    }

    public int getScore() {
        return score;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCorrAnswer(String corrAnswer) {
        this.corrAnswer = corrAnswer;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return id + " - " + text + " - " + score;
    }
}
