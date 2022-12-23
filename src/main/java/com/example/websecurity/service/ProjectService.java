package com.example.websecurity.service;

import com.example.websecurity.dao.model.Project;
import com.example.websecurity.dao.model.User;

import java.util.List;

public interface ProjectService {

    List<Project> getAll();

    Project getById(Long id);

    Project create(Project project);

    boolean delete(Long id);

    boolean applyForProject(User user, Long projectId);
}
