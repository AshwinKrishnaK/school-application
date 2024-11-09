package com.threadsafe.school.repo;

import com.threadsafe.school.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for School class entity operations.
 * Extends JpaRepository to provide CRUD operations for School class entity.
 */
@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass,Integer> {

}
