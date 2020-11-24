package com.example.swarangigaurkar.quizzup;

public class Questions {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int ansNr;
    private int questionId;
    private int categoryId;

    public Questions(String question, String option1, String option2, String option3, String option4, int ansNr,int questionId,int categoryId) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ansNr = ansNr;
        this.categoryId = categoryId;
        this.questionId = questionId;
    }

    public Questions() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnsNr() {
        return ansNr;
    }

    public void setAnsNr(int ansNr) {
        this.ansNr = ansNr;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
