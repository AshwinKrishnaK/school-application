package com.threadsafe.school.controller;

import com.threadsafe.school.execptions.UserNotFoundException;
import com.threadsafe.school.model.Student;
import com.threadsafe.school.service.StudentService;
import com.threadsafe.school.model.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.threadsafe.school.constants.SchoolConstants.SUCCESS;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Response> getAllStudents() {
        return ResponseEntity.ok(new Response(studentService.getAllStudents(), SUCCESS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getStudent(@PathVariable("id") int id)
            throws UserNotFoundException {
        return ResponseEntity.ok(new Response(studentService.getStudentDetails(id), SUCCESS));
    }

    @PostMapping
    public ResponseEntity<Response> createStudent(@Valid @RequestBody Student student) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(studentService.createStudent(student), SUCCESS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content for successful delete
    }

    @PutMapping
    public ResponseEntity<Response> updateStudent(@RequestBody Student student) throws UserNotFoundException {
        return ResponseEntity.ok(new Response(studentService.updateStudent(student),SUCCESS)); //
    }
}
