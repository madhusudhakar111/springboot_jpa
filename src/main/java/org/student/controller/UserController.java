package org.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.student.entity.User;
import org.student.service.UserService;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveuser")
    public ResponseEntity<String> save(@RequestBody User user){
        try {
            userService.save(user);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure" + e.getMessage());
        }
    }

    @GetMapping("/getuserlist")
    public ResponseEntity<?> getUserList(){
        try {
            List<User> usrList = userService.getUserList();
            return ResponseEntity.ok(usrList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Failure", e.getMessage()));
        }
    }

    @GetMapping("/getuser")
    public ResponseEntity<?> getUser(@RequestParam String id){
        try {
            User  usr = userService.getUserbyID(Long.parseLong(id));
            return ResponseEntity.ok(usr);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Failure", e.getMessage()));
        }
    }


    @DeleteMapping("/deleteallusers")
    public ResponseEntity<String> deleteAllUsers(){
        try {
            userService.deleteAllUsers();
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure" + e.getMessage());
        }
    }

    @DeleteMapping("/deleteusers")
    public ResponseEntity<String> deleteUsers(@RequestParam String id){
        try {
            userService.deleteUser(Long.parseLong(id));
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure" + e.getMessage());
        }
    }


    @GetMapping("/getusernames")
    public ResponseEntity<?> getUserNamesLike(@RequestParam String name){
        try {
            List<User> usrList = userService.getUserNamesLike(name);
            return ResponseEntity.ok(usrList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Failure", e.getMessage()));
        }
    }


    @GetMapping("/findbyname")
    public ResponseEntity<?> findByName(@RequestParam String name){
        try {
            List<User> usrList = userService.findByName(name);
            return ResponseEntity.ok(usrList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Failure", e.getMessage()));
        }
    }


    @GetMapping("/getuserbycity")
    public ResponseEntity<?> getUserByCity(@RequestParam String city){
        try {
            List<User> usrList = userService.getUserByCity(city);
            return ResponseEntity.ok(usrList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Failure", e.getMessage()));
        }
    }

    @GetMapping("/gettotalusercount")
    public ResponseEntity<?> getTotalUserCount(){
        try {
            Integer count = userService.getTotalUserCount();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Failure", e.getMessage()));
        }
    }


    @GetMapping("/getuserpage")
    public ResponseEntity<?> getUserPage(@RequestParam int page, @RequestParam int size){
        try {
            Page<User> userPage = userService.getUserPage(page, size);
            return ResponseEntity.ok(userPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Failure", e.getMessage()));
        }
    }









}
