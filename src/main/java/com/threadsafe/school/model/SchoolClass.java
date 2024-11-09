package com.threadsafe.school.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "class")
@Getter
@Setter
public class SchoolClass extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
    private String name;

    @OneToMany(mappedBy = "schoolClass",fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Student.class)
    private Set<Student> students;
}
