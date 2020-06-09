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
        List<Map<String, String>> gradesList = new ArrayList();
        Map<String, String> gradeMap;
        try {
            User user = userService.findById(user_id);
            if (user.getRole().equals(Role.TEACHER)) {
                exams = examService.getExamByTeacher(user_id);
            } else if (user.getRole().equals(Role.STUDENT)) {
                exams = examService.getFinishedExamByStudentID(user_id);
            }
            for (Exam exam : exams) {
                gradeMap = new HashMap<>();
                if (gradeService.getGradeByStudentAndExam(user_id, exam.getId()).size() > 0) {

                    List<Question> question = questionService.findQuestionByExamId(exam.getId());

                    Grade grade = gradeService.getGradeByStudentAndExam(user_id, exam.getId()).get(0);
                    List<StudentsAnswers> studentsAnswers = gradeService.getGradeByStudentAndExam(user_id, exam.getId()).get(0).getStudentsAnswers();

                    for (Question quiz : question) {

                        for (StudentsAnswers studentsAnswers1 : studentsAnswers) {

                           // QuestionChoices choice = questionChoicesService.getChoiceByIdAndExamIdandQuestionId(studentsAnswers1.getChoiceId(), studentsAnswers1.getExamId(), studentsAnswers1.getQuestionId());

//                            for (QuestionChoices questionChoices : quiz.getQuestionChoices()) {
//                                if (studentsAnswers1.getQuestionId().equals(questionChoices.getQuestionid()) &&
//                                        studentsAnswers1.getExamId().equals(questionChoices.getExamid())) {
//                                    System.out.println(studentsAnswers1.getChoiceId());
//                                }
//                            }
                        }
                    }


                    //studentsAnswers.get(0).ge
                    gradeMap.put("exam_name", exam.getExamname());
                    gradeMap.put("grade", grade.getGrade());
                    gradeMap.put("correct_answer_count", grade.getCorrect_answer_count() + "");
                    gradeMap.put("question_count", grade.getQuestions_count() + "");
                    gradesList.add(gradeMap);
                }
            }

            model.addAttribute("user", user);
            //  model.addAttribute("gradeMap", gradeMap);
            model.addAttribute("gradesMap", gradesList);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "view_result";
    }
}
