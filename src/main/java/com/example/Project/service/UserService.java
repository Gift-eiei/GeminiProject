package com.example.Project.service;
import com.example.Project.Controller.Object.RegisObj;
import com.example.Project.Model.User.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(RegisObj registration);
}