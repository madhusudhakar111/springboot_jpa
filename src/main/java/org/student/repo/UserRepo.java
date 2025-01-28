package org.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.student.entity.User;

import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    // Native Queries Example's

    @Query(value="SELECT * FROM user u WHERE u.user_name LIKE %:name%", nativeQuery = true)
    public List<User> getUserNamesLike(@Param("name") String name);

    @Query(value="SELECT * FROM user u WHERE u.state =:state", nativeQuery = true)
    public List<User> getUserState(@Param("state") String state);


    // JPQL example

    @Query("SELECT u FROM User u WHERE u.state = :state")
    List<User> findUsersByState(@Param("state") String state);


}
