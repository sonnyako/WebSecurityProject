package com.example.websecurity.dao.repository;

import com.example.websecurity.dao.model.UserProject;
import com.example.websecurity.dao.model.UserProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectId> {
}
