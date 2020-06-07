package com.lms.service;

import com.lms.dao.StudentsExamsRepository;
import com.lms.model.StudentsExams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Created by Bhanuka
 * */

@Service
public class StudentExamsServiceImpl implements StudentExamsService{

    @Autowired
    private StudentsExamsRepository studentsExamsRepository;

    @Override
    public StudentsExams save(StudentsExams studentsExams) {
        return studentsExamsRepository.save(studentsExams);
    }

    @Override
    public List<StudentsExams> getAll() {
        return null;
    }

    @Override
    public void delete(StudentsExams studentsExams) {

    }
}
