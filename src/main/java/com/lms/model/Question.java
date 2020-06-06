package com.lms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by Bhanuka
 * */

@Document
public class Question implements Serializable {

    @Id
    private String id;

    @Indexed(name = "examid_idx", background = true)
    private String examid;
    private String question;
    private Boolean status;
    private List<QuestionChoices> questionChoices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public List<QuestionChoices> getQuestionChoices() {
        return questionChoices;
    }

    public void setQuestionChoices(List<QuestionChoices> questionChoices) {
        this.questionChoices = questionChoices;
    }

}
