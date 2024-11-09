package com.threadsafe.school.service;

import com.threadsafe.school.execptions.UserNotFoundException;
import com.threadsafe.school.model.Address;
import com.threadsafe.school.model.Teacher;

import java.util.List;

/**
 * Service interface for Teacher entity operations.
 * Defines methods to manage Teacher entities, including CRUD operations.
 */
public interface TeacherService {

    /**
     * Updates the address of a teacher by their ID.
     *
     * @param id the ID of the teacher whose address needs to be updated
     * @param address the new address details to be set for the teacher
     * @return a message indicating the outcome of the update operation
     * @throws UserNotFoundException if the teacher is not found
     */
    public String updateTeacherAddress(int id, Address address) throws UserNotFoundException;

    /**
     * Retrieves a list of all teachers.
     *
     * @return a list of Teacher objects
     */
    public List<Teacher> getAllTeachers();

    /**
     * Creates a new teacher.
     *
     * @param teacher the Teacher object containing the details of the teacher to be created
     * @return a message indicating the outcome of the creation operation
     * @throws Exception if an error occurs during teacher creation
     */
    public String createTeacher(Teacher teacher) throws Exception;

    /**
     * Updates the details of an existing teacher.
     *
     * @param teacher the Teacher object containing the updated details
     * @return a message indicating the outcome of the update operation
     * @throws UserNotFoundException if the teacher to be updated is not found
     */
    public String updateTeacher(Teacher teacher) throws UserNotFoundException;

    /**
     * Deletes a teacher by their ID.
     *
     * @param id the ID of the teacher to be deleted
     * @return a message indicating the outcome of the delete operation
     */
    public String deleteTeacher(int id);
}

