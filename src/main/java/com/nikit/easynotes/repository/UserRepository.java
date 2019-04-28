package com.nikit.easynotes.repository;

import com.nikit.easynotes.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    @Query("select u from Users u where id=?1")
    Users findUserById(Long userId);

    @Query("select u from Users u where u.status=0")
    List<Users> findActiveUsers();

    @Query("select u from Users u where u.status=1")
    List<Users> findInActiveUsers();

    @Query("select u from Users u where u.id=?1 and u.status=0")
    Users findActiveUser(Long userId);
}
