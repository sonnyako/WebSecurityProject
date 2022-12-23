package com.example.websecurity.dao.repository;

import com.example.websecurity.dao.model.Role;
import com.example.websecurity.dao.query.RoleQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = RoleQuery.FIND_BY_NAME,
            nativeQuery = true)
    Role getByName(@Param("name") String name);
}
