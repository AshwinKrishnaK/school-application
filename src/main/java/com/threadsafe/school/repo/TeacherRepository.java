package com.threadsafe.school.repo;

import com.threadsafe.school.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Teacher entity operations.
 * Extends JpaRepository to provide CRUD operations for Teacher entity.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
