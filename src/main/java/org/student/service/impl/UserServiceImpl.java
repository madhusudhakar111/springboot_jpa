package org.student.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.student.entity.User;
import org.student.repo.UserRepo;
import org.student.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

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

    // Stored procedure example

    @Override
    @Transactional
    public List<User> getUserByCity(String city){
        return userRepo.getUserByCity(city);
    }

    // JPQL example's

    @Override
    public  List<User> findByName(String name){
        return userRepo.findByNameCustom(name);
    }

    @Override
    public Integer getTotalUserCount(){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetTotalUsers");
        query.registerStoredProcedureParameter("totalUsers", Integer.class, ParameterMode.OUT);
        query.execute();
        Integer count  = (Integer) query.getOutputParameterValue("totalUsers");

         return count;
    }

    // Pagination

    @Override
    public Page<User> getUserPage(int page, int size){
        return userRepo.findAll(PageRequest.of(page, size));
    }


}
