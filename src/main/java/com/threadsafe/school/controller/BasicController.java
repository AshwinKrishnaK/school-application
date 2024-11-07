package com.threadsafe.school.controller;

import com.threadsafe.school.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.threadsafe.school.repo.UserRepository;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api")
@Slf4j
public class BasicController {

    private final UserRepository userRepository;

    @Autowired
    public BasicController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @PostAuthorize("returnObject.userId == authentication.principal.userId")
    @GetMapping("/test")
    public User getStudentDetails(@Param("id") Long id) throws NoSuchElementException {
        log.info("id is {}",id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getPrincipal();
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
    }
}
