package com.lms.model;

/*
 * Created by Bhanuka
 * */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class QuestionChoices implements Serializable {

    @Id
    private String id;

    @Indexed(name = "examid_idx", background = true)
    private String examid;

    @Indexed(name = "questionid_idx", background = true)
    private String questionid;
    private String choice;
    private Boolean iscorrect;

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

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoce(String choice) {
        this.choice = choice;
    }

    public Boolean getIscorrect() {
        return iscorrect;
    }

    public void setIscorrect(Boolean iscorrect) {
        this.iscorrect = iscorrect;
    }
}
