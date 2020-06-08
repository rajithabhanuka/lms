package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.constant.Constants;
import com.lms.model.*;
import com.lms.service.ExamService;
import com.lms.service.GradeService;
import com.lms.service.QuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/*
 * Created by Bhanuka
 * */

@Controller
public class ExamController {

    private static final Logger LOGGER = LogManager.getLogger(ExamController.class);

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private GradeService gradeService;

    //TODO comments should be added
    //TODO error handling should be added

    @RequestMapping(value = "/create_exam", method = RequestMethod.GET)
    public String create_exam_view(HttpServletRequest request, Model model) {
        return "create_exam";
    }

    @ResponseBody
    @RequestMapping(value = "/create_exam", method = RequestMethod.POST, consumes = "application/json")
    public String create_exam(HttpServletRequest request, @RequestBody String ExamJson) {

        String response = "{}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Exam exam = objectMapper.readValue(ExamJson, Exam.class);

            if (!exam.getExamname().isEmpty() || !exam.getExamname().equals("")) {

                exam.setCreatedDate(new Date());

                Exam created = examService.save(exam);

                if (created != null) {
                    response = new CustomResponse(Constants.SUCCESS, "SUCCESS").toJson();
                } else {
                    response = new CustomResponse(Constants.FAIL, "FAIL").toJson();
                }

            } else {
                response = new CustomResponse(Constants.MISSING_DATA, "FAIL").toJson();
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
            response = new CustomResponse(Constants.CREATE_ERROR, "FAIL").toJson();
        }

        return response;
    }

    @RequestMapping(value = "/launch_exam", method = RequestMethod.POST)
    public String launch_exam_view(HttpServletRequest request, Model model) {
        try {
            String selected_exam_id = request.getParameter("selected_exam_id");
            Exam exam = examService.findById(selected_exam_id);
            List<Question> questions = questionService.findQuestionByExamId(exam.getId());
            model.addAttribute("exam", exam);
            model.addAttribute("questions", questions);
            request.getSession().setAttribute("questions", questions);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return "launch_exam";
    }


    @RequestMapping(value = "/launch_exam_progress", method = RequestMethod.GET)
    public String launch_exam_progress(HttpServletRequest request, Model model) {
        try {

            int currentQuestionID = 0;
            Map<String, String> answermap = null;
            if (request.getSession().getAttribute("answermap") == null) {
                answermap = new HashMap<>();
            } else {
                answermap = (Map<String, String>) request.getSession().getAttribute("answermap");
            }

            List<Question> questions = (List<Question>) request.getSession().getAttribute("questions");

            Object object = request.getSession().getAttribute("currentQuestionID");
            String questionid = (String) request.getParameter("questionid");
            String answer = (String) request.getParameter("answer");

            if (!questionid.equals("0") && !answer.equals("undefined")) {
                answermap.put(questionid, answer);
            }

            if (object == null) {
                currentQuestionID = 0;
            } else {
                currentQuestionID = (Integer) request.getSession().getAttribute("currentQuestionID");
            }

            String add = request.getParameter("add");
            String deduct = request.getParameter("deduct");

            if (add != null && object != null && add.equals("1") && currentQuestionID < questions.size() - 1) {
                currentQuestionID += 1;
            }

            if (deduct != null && object != null && deduct.equals("1") && currentQuestionID > 0) {
                currentQuestionID -= 1;
            }

            request.getSession().setAttribute("currentQuestionID", currentQuestionID);
            request.getSession().setAttribute("question_id", questions.get(currentQuestionID).getId());
            request.getSession().setAttribute("answermap", answermap);
            model.addAttribute("currentQuestionID", currentQuestionID);
            model.addAttribute("question", questions.get(currentQuestionID));
            model.addAttribute("answermap", answermap);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return "launch_exam_progress";
    }


    @ResponseBody
    @RequestMapping(value = "/finish_exam", method = RequestMethod.POST, consumes = "application/json")
    public String finish_exam(HttpServletRequest request, @RequestBody String FinishExamJson) {
        String response = "{}";

        try {

            Map<String, String> answermap = null;
            List<StudentsAnswers> studentsAnswers  = new ArrayList<>();

            if (request.getSession().getAttribute("answermap") != null) {

                answermap = (Map<String, String>) request.getSession().getAttribute("answermap");

                ObjectMapper objectMapper = new ObjectMapper();
                StudentsAnswers studentsAnswerDetails = objectMapper.readValue(FinishExamJson, StudentsAnswers.class);

                Grade grade = new Grade();
                grade.setExamId(studentsAnswerDetails.getExamId());
                grade.setStudentId(studentsAnswerDetails.getStudentId());
                grade.setGrade("Not-Marked");
                grade.setStatus("Not-Synced");

                StudentsAnswers studentsAnswer;
                for (Map.Entry<String, String> entry : answermap.entrySet()) {
                    studentsAnswer = new StudentsAnswers();
                    studentsAnswer.setExamId(studentsAnswerDetails.getExamId());
                    studentsAnswer.setStudentId(studentsAnswerDetails.getStudentId());
                    studentsAnswer.setQuestionId(entry.getKey());
                    studentsAnswer.setChoiceId(entry.getValue());
                    studentsAnswer.setCompletedTime(new Date());
                    studentsAnswer.setIsRight("Not-Marked" +
                            "" +
                            "" +
                            "");
                    studentsAnswers.add(studentsAnswer);
                }

                grade.setStudentsAnswers(studentsAnswers);

                Grade created = gradeService.save(grade);

                if (created != null) {
                    response = new CustomResponse(Constants.SUCCESS, "SUCCESS").toJson();
                } else {
                    response = new CustomResponse(Constants.FAIL, "FAIL").toJson();
                }

            } else {
                response = new CustomResponse(Constants.MISSING_DATA, "FAIL").toJson();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            response = new CustomResponse(Constants.CREATE_ERROR, "FAIL").toJson();
        }

        return response;
    }

}
