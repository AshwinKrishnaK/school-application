package com.threadsafe.school.service;

import com.threadsafe.school.repo.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.threadsafe.school.execptions.UserNotFoundException;
import com.threadsafe.school.model.Student;
import com.threadsafe.school.model.SchoolClass;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private SchoolClassRepository classRepository;

    public Set<String> getAllClass(){
        return classRepository.findAll().stream().map(SchoolClass::getName)
                .collect(Collectors.toSet());
    }

    public Set<Student> getAllStudent(int classId) throws UserNotFoundException {
        var schoolClass = classRepository.findById(classId).orElseThrow(()-> new UserNotFoundException(" Not such class exists with this id "+classId));
        return schoolClass.getStudents();
    }
}
