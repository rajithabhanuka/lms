package com.lms.controller;

import com.lms.model.*;
import com.lms.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created by Bhanuka
 * */

@Controller
public class GradeController {

    private static final Logger LOGGER = LogManager.getLogger(ExamController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ExamService examService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentExamsService studentExamsService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/view_result", method = RequestMethod.GET)
    public String view_result(HttpServletRequest request, Model model) {

        String user_id = (String) request.getSession().getAttribute("USER_ID");
        List<Exam> exams = null;
        List<Grade> grades = new ArrayList<>();
        Map<Integer, Map<String, String>> gradesMap = new HashMap<>();
        Map<String, String> gradeMap = new HashMap<>();
        try {
            User user = userService.findById(user_id);
            if (user.getRole().equals(Role.TEACHER)) {
                exams = examService.getExamByTeacher(user_id);
            } else if (user.getRole().equals(Role.STUDENT)) {
                exams = examService.getFinishedExamByStudentID(user_id);
            }
            int i = 0;
            for (Exam exam : exams) {
                if (gradeService.getGradeByStudentAndExam(user_id, exam.getId()).size() > 0) {
                    //   grades.add(gradeService.getGradeByStudentAndExam(user_id, exam.getId()).get(0));

                    Grade grade = gradeService.getGradeByStudentAndExam(user_id, exam.getId()).get(0);
                    List<Question> question = questionService.findQuestionByExamId(exam.getId());

                    for (Question quiz : question) {
                        List<StudentsAnswers> studentsAnswers = gradeService.getGradeByStudentAndExam(user_id, exam.getId()).get(0).getStudentsAnswers();
                        for (StudentsAnswers studentsAnswers1 : studentsAnswers) {
                            for (QuestionChoices questionChoices : quiz.getQuestionChoices()) {
                                if (questionChoices.getQuestionid().equals(studentsAnswers1.getQuestionId())) {
                                    studentsAnswers1.getChoiceId();
                                }
                            }
                        }
                    }


                    //studentsAnswers.get(0).ge
                    i++;
                    gradeMap.put("exam_name", exam.getExamname());
                    gradeMap.put("grade", grade.getGrade());
                    gradeMap.put("correct_answer_count", grade.getCorrect_answer_count() + "");
                    gradeMap.put("question_count", grade.getQuestions_count() + "");

                    gradesMap.put(i, gradeMap);

                }
            }

            model.addAttribute("user", user);
            //  model.addAttribute("gradeMap", gradeMap);
            model.addAttribute("gradesMap", gradesMap);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "view_result";
    }
}
