package com.demo.appswave.NewsMangement.service;

import com.demo.appswave.NewsMangement.entities.News;
import com.demo.appswave.NewsMangement.entities.User;
import com.demo.appswave.NewsMangement.enumeration.ERole;
import com.demo.appswave.NewsMangement.enumeration.NewsStatus;
import com.demo.appswave.NewsMangement.repository.RoleRepository;
import com.demo.appswave.NewsMangement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.appswave.NewsMangement.entities.Role;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User registerUser(User user) throws Exception {
        
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Role userRole = roleRepository.findByName(ERole.ROLE_NORMAL)
                .orElseThrow(() -> new Exception("Role not found"));
        user.getRoles().add(userRole);// Assign default role
        return userRepository.save(user);
    }

    public User assignRoleToUser(String email, ERole eRole) throws Exception {
        Role userRole = roleRepository.findByName(eRole)
                .orElseThrow(() -> new Exception("Role not found"));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.getRoles().add(userRole);
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User updateUser(Long id, User updatedUser) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFullName(updatedUser.getFullName());
            user.setEmail(updatedUser.getEmail());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
            return userRepository.save(user);
        } else {
            throw new Exception("User not found with id " + id);
        }
    }
    public void deleteUser(Long id) throws Exception {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id " + id));
        userRepository.delete(user);

    }

}
