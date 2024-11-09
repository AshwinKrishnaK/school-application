package com.threadsafe.school.service;

import com.threadsafe.school.execptions.UserNotFoundException;
import com.threadsafe.school.model.Address;
import com.threadsafe.school.model.Role;
import com.threadsafe.school.model.Teacher;
import com.threadsafe.school.repo.RoleRepository;
import com.threadsafe.school.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.threadsafe.school.constants.SchoolConstants.*;

@Slf4j
@Service
public class TeacherServiceImpl {

    private final TeacherRepository teacherRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, RoleRepository roleRepository) {
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
    }

    public String updateTeacherAddress(int id, Address address) throws UserNotFoundException {
        log.info("Updating address for teacher with ID: {}", id);
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Teacher with id is not found " + id));
        Address teacherAddress = existingTeacher.getAddress();
        teacherAddress.setAddress1(address.getAddress1());
        teacherAddress.setAddress2(address.getAddress2());
        teacherAddress.setCity(address.getCity());
        teacherAddress.setState(address.getState());
        teacherAddress.setZipCode(address.getZipCode());
        existingTeacher.setAddress(teacherAddress);
        teacherRepository.save(existingTeacher);
        return UPDATED;
    }

    public List<Teacher> getAllTeachers() {
        log.info("Retrieving all teachers");
        return teacherRepository.findAll();
    }

    public String createTeacher(Teacher teacher) throws Exception {
        log.info("Creating teacher with email: {}", teacher.getUser().getEmail());
        Optional<Role> teacherRole = roleRepository.findByName(TEACHER);
        if (teacherRole.isPresent()) {
            Set<Role> roles = new HashSet<>();
            roles.add(teacherRole.get());
            teacher.getUser().setRoles(roles);
            teacherRepository.save(teacher);
            return SAVED;
        } else {
            throw new Exception("Role not found");
        }
    }

    public String updateTeacher(Teacher teacher) throws UserNotFoundException {
        log.info("Updating teacher with ID: {}", teacher.getTeacherId());
        Teacher existingTeacher = teacherRepository.findById(teacher.getTeacherId())
                .orElseThrow(() -> new UserNotFoundException("Teacher with id is not found " + teacher.getTeacherId()));
        existingTeacher.getUser().setEmail(teacher.getUser().getEmail());
        existingTeacher.getUser().setName(teacher.getUser().getName());
        existingTeacher.getUser().setPassword(teacher.getUser().getPassword());
        existingTeacher.setAddress(teacher.getAddress());
        teacherRepository.save(existingTeacher);
        return UPDATED;
    }

    public String deleteTeacher(int id) {
        log.info("Deleting teacher with ID: {}", id);
        teacherRepository.deleteById(id);
        return DELETED;
    }
}
