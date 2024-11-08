package com.threadsafe.school.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "class")
@Data
public class SchoolClass extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
    private String name;

    @OneToMany(mappedBy = "schoolClass",fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = Student.class)
    private Set<Student> students;
}
