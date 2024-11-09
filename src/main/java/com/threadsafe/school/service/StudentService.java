package com.threadsafe.school.service;

import com.threadsafe.school.execptions.UserNotFoundException;
import com.threadsafe.school.model.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentService {

    /**
     * Retrieves the details of a specific student by their ID.
     *
     * @param id the ID of the student to retrieve
     * @return the Student object corresponding to the given ID
     * @throws UserNotFoundException if the student is not found
     */
    public Student getStudentDetails(@Param("id") int id) throws UserNotFoundException;

    /**
     * Deletes a student by their ID.
     *
     * @param id the ID of the student to delete
     * @return a message indicating the outcome of the delete operation
     */
    public String deleteStudent(int id);

    /**
     * Retrieves a list of all students.
     *
     * @return a list of all Student objects
     */
    public List<Student> getAllStudents();

    /**
     * Creates a new student.
     *
     * @param student the Student object containing the student details to be created
     * @return a message indicating the outcome of the creation operation
     * @throws Exception if an error occurs during student creation
     */
    public String createStudent(Student student) throws Exception;

    /**
     * Updates an existing student's details.
     *
     * @param student the Student object containing the updated student details
     * @return a message indicating the outcome of the update operation
     * @throws UserNotFoundException if the student to update is not found
     */
    public String updateStudent(Student student) throws UserNotFoundException;
}
