package com.sekolah.websekolah.repository;


import com.sekolah.websekolah.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email=:email")
    User findByEmailId(@Param("email") String email);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User>findByName(String name);
}
