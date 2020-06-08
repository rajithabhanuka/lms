package com.lms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/*
 * Created by Bhanuka
 * */

@Document
public class Grade implements Serializable {

    @Id
    private String id;

    @Indexed(name = "examId_idx", background = true)
    private String examId;

    @Indexed(name = "studentId_idx", background = true)
    private String studentId;
    private String grade;
    private String status;
    List<StudentsAnswers> studentsAnswers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StudentsAnswers> getStudentsAnswers() {
        return studentsAnswers;
    }

    public void setStudentsAnswers(List<StudentsAnswers> studentsAnswers) {
        this.studentsAnswers = studentsAnswers;
    }
}
