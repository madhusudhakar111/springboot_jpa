package org.student.service;

import org.springframework.data.domain.Page;
import org.student.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> getUserList();

    User getUserbyID(Long id);

    void deleteAllUsers();

    void deleteUser(Long id);

    List<User> getUserNamesLike(String name);

    List<User> getUserByCity(String city);

    List<User> findByName(String name);

    Integer getTotalUserCount();

    Page<User> getUserPage(int page, int size);
}
