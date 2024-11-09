package com.threadsafe.school.service;

import com.threadsafe.school.execptions.UserNotFoundException;
import com.threadsafe.school.execptions.RoleNotFoundException;
import com.threadsafe.school.model.Role;
import com.threadsafe.school.model.Student;
import com.threadsafe.school.repo.RoleRepository;
import com.threadsafe.school.repo.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.threadsafe.school.constants.SchoolConstants.*;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostAuthorize("returnObject.userId == authentication.principal.userId")
    public Student getStudentDetails(int id) throws UserNotFoundException {
        log.info("Retrieving student details for ID: {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Student not found with ID " + id));
    }

    @PreAuthorize("hasRole('TEACHER')")
    public String deleteStudent(int id) {
        log.info("Deleting student with ID: {}", id);
        studentRepository.deleteById(id);
        return DELETED;
    }

    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public List<Student> getAllStudents() {
        log.info("Retrieving all students");
        return studentRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public String createStudent(Student student) throws RoleNotFoundException {
        log.info("Creating student with email: {}", student.getUser().getEmail());
        Role role = roleRepository.findByName(STUDENT)
                .orElseThrow(() -> new RoleNotFoundException("Student role not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        student.getUser().setRoles(roles);
        student.getUser().setPassword(passwordEncoder.encode(student.getUser().getPassword()));
        studentRepository.save(student);
        return SAVED;
    }

    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public String updateStudent(Student student) throws UserNotFoundException {
        log.info("Updating student with ID: {}", student.getStudentId());
        var existingStudent = studentRepository.findById(student.getStudentId())
                .orElseThrow(() -> new UserNotFoundException("Student with ID " + student.getStudentId() + " not found"));

        existingStudent.getUser().setEmail(student.getUser().getEmail());
        existingStudent.getUser().setName(student.getUser().getName());
        existingStudent.getUser().setPassword(passwordEncoder.encode(student.getUser().getPassword()));
        existingStudent.setAddress(student.getAddress());
        studentRepository.save(existingStudent);
        return UPDATED;
    }
}
