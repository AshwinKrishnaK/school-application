package com.threadsafe.school.controller;


import com.threadsafe.school.execptions.UserNotFoundException;
import com.threadsafe.school.model.Response;
import com.threadsafe.school.model.Teacher;
import com.threadsafe.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.threadsafe.school.constants.SchoolConstants.SUCCESS;


@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<Response> getAllTeachers(@RequestParam(value = "subject", required = false) String subject) {
        return ResponseEntity.ok(new Response(teacherService.getAllTeachers(), SUCCESS));
    }

    @PostMapping
    public ResponseEntity<Response> createTeacher(@RequestBody Teacher teacher) throws Exception {
        return ResponseEntity.ok(new Response(teacherService.createTeacher(teacher), SUCCESS));
    }

    @DeleteMapping
    public ResponseEntity<Response> deleteTeacher(@PathVariable("id") int id) throws UserNotFoundException {
        return ResponseEntity.ok(new Response(teacherService.deleteTeacher(id), SUCCESS));
    }

    @PutMapping
    public ResponseEntity<Response> updateTeacher(@RequestBody Teacher teacher) throws UserNotFoundException {
        return ResponseEntity.ok(new Response(teacherService.updateTeacher(teacher), SUCCESS));
    }

}
