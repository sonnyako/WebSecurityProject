package com.example.websecurity.service.impl;

import com.example.websecurity.dao.model.*;
import com.example.websecurity.dao.repository.ProjectRepository;
import com.example.websecurity.dao.repository.UserProjectRepository;
import com.example.websecurity.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final UserProjectRepository userProjectRepository;

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(Long id) {
        var project = projectRepository.findById(id);

        if (project.isPresent()) return project.get();
        else
            throw new EntityNotFoundException("Can't find project with id: {id}");
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public boolean delete(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean applyForProject(User user, Long projectId) {
        var isOwner = user.getRole().getRoleName() == RoleName.ROLE_OWNER;

        if (projectRepository.existsById(projectId)) {
            userProjectRepository.save((UserProject.builder()
                    .id(UserProjectId.builder()
                            .projectId(projectId)
                            .userId(user.getId())
                            .build())
                    .isUserApprovedForProject(false)
                    .isUserOwner(isOwner)
                    .build()));
            return true;
        } else throw new EntityNotFoundException("Project not exists");
    }
}
