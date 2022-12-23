package com.example.websecurity.dao.repository;

import com.example.websecurity.dao.model.User;
import com.example.websecurity.dao.query.UserQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = UserQuery.FIND_BY_USERNAME, nativeQuery = true)
    User findByUsername(@Param("username") String username);

    @Query(value = UserQuery.FIND_BY_ROLE, nativeQuery = true)
    List<User> findAllByRole(@Param("roleId") Long roleId);

}
