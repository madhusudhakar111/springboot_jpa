package org.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.student.entity.User;
import org.student.repo.UserRepo;
import org.student.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void save(User user){
        userRepo.save(user);
    }

    @Override
    public List<User> getUserList(){
        return userRepo.findAll();
    }

    @Override
    public User getUserbyID(Long id){
        return userRepo.findById(id).orElseThrow(()-> new NoSuchElementException("User id Not found"));
    }


    @Override
    public void deleteAllUsers(){
        userRepo.deleteAll();
    }

    @Override
    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

    @Override
    public List<User> getUserNamesLike(String name){
        return userRepo.getUserNamesLike(name);
    }


    // JPQL example's

    @Override
    public  List<User> findByName(String name){
        return userRepo.findByNameCustom(name);
    }



}
