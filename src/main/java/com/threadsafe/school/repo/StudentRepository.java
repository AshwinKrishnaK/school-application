package com.threadsafe.school.repo;

import com.threadsafe.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Student entity operations.
 * Extends JpaRepository to provide CRUD operations for Student entity.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
