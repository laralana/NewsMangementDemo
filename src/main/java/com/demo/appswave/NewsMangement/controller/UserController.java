package com.demo.appswave.NewsMangement.controller;

import com.demo.appswave.NewsMangement.entities.News;
import com.demo.appswave.NewsMangement.entities.User;
import com.demo.appswave.NewsMangement.enumeration.ERole;
import com.demo.appswave.NewsMangement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{email}/role")
    public ResponseEntity<Object> assignRoleToUser(@PathVariable String email, @RequestParam ERole ERole) {
        User updatedUser = null;
        try {
            updatedUser = userService.assignRoleToUser(email, ERole);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("message", e.getMessage());
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedUser);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User updatedUser)  {
        try {
            return ResponseEntity.ok(userService.updateUser(id, updatedUser));
        } catch (Exception e) {
            logger.error(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("message", e.getMessage());
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("message", e.getMessage());
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("User Deleted Successfully");
    }

}
