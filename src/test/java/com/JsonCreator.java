package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.model.Question;
import com.lms.model.QuestionChoices;

import java.util.ArrayList;
import java.util.List;

public class JsonCreator {
    public static void main(String[] args) {
        try {
            Question question = new Question();

            question.setExamid("5edb8423d0a82522ed2a3b57");
            question.setQuestion("What is the output 9/3");
            question.setStatus(true);


            QuestionChoices questionChoices1 = new QuestionChoices();
            questionChoices1.setId("1");
            questionChoices1.setChoce("1");
            questionChoices1.setExamid("5edb8423d0a82522ed2a3b57");
            questionChoices1.setQuestionid("1");
            questionChoices1.setIscorrect(false);

            QuestionChoices questionChoices2 = new QuestionChoices();
            questionChoices2.setId("1");
            questionChoices2.setChoce("3");
            questionChoices2.setExamid("5edb8423d0a82522ed2a3b57");
            questionChoices2.setQuestionid("1");
            questionChoices2.setIscorrect(false);

            QuestionChoices questionChoices3 = new QuestionChoices();
            questionChoices3.setId("1");
            questionChoices3.setChoce("3");
            questionChoices3.setExamid("5edb8423d0a82522ed2a3b57");
            questionChoices3.setQuestionid("1");
            questionChoices3.setIscorrect(true);

            QuestionChoices questionChoices4 = new QuestionChoices();
            questionChoices4.setId("1");
            questionChoices4.setChoce("4");
            questionChoices4.setExamid("5edb8423d0a82522ed2a3b57");
            questionChoices4.setQuestionid("1");
            questionChoices4.setIscorrect(false);

            QuestionChoices questionChoices5 = new QuestionChoices();
            questionChoices5.setId("1");
            questionChoices5.setChoce("4");
            questionChoices5.setExamid("5edb8423d0a82522ed2a3b57");
            questionChoices5.setQuestionid("1");
            questionChoices5.setIscorrect(false);

            List<QuestionChoices> questionChoices = new ArrayList<>();
            questionChoices.add(questionChoices1);
            questionChoices.add(questionChoices2);
            questionChoices.add(questionChoices3);
            questionChoices.add(questionChoices4);
            questionChoices.add(questionChoices5);

            question.setQuestionChoices(questionChoices);

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(question);
            System.out.println(jsonStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
