package org.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
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


    // Stored procedure example

  //  @Query(value="CALL GetUsersByCity(:city)", nativeQuery = true)
   // public List<User> getUserByCity(@Param("city") String city);

    /*

        DELIMITER $$
        CREATE PROCEDURE GetUsersByCity(IN cityName VARCHAR(255))
        BEGIN
        SELECT * FROM user WHERE city = cityName;
        END $$
        DELIMITER ;
    */


    // JPQL example

    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> findByNameCustom(@Param("name") String name);


    @Query("SELECT u FROM User u WHERE u.state = :state")
    List<User> findUsersByState(@Param("state") String state);


    @Query("SELECT u FROM User u WHERE u.name = :name AND u.city = :city")
    List<User> findUsersByNameAndCity(@Param("name") String name, @Param("city") String city);


    @Query("SELECT u FROM User u WHERE u.email LIKE %:email%")
    List<User> findUsersByEmailContaining(@Param("email") String email);


    @Query("SELECT u FROM User u WHERE u.name = :name OR u.email = :email")
    List<User> findUsersByNameOrEmail(@Param("name") String name, @Param("email") String email);


    @Query("SELECT u FROM User u ORDER BY u.name ASC")
    List<User> findAllUsersOrderByNameAsc();


    @Query("SELECT u FROM User u WHERE u.id BETWEEN :idfrom AND :idto")
    List<User> findUsersByZipRange(@Param("idfrom") String idfrom, @Param("idto") String idto);

    @Query("SELECT u FROM User u WHERE u.phone IS NULL")
    List<User> findUsersWithoutPhone();


    @Query("SELECT u FROM User u WHERE u.state IN :states")
    List<User> findUsersByStates(@Param("states") List<String> states);


    @Query("SELECT COUNT(u) FROM User u WHERE u.state = :state")
    long countUsersByState(@Param("state") String state);


    @Modifying
    @Query("DELETE FROM User u WHERE u.state = :state")
    void deleteUsersByState(@Param("state") String state);



    @Modifying
    @Query("UPDATE User u SET u.city = :city WHERE u.state = :state")
    void updateCityByState(@Param("city") String city, @Param("state") String state);


    @Procedure(procedureName = "GetUsersByCity")
    public List<User> getUserByCity(@Param("city") String city);







}
