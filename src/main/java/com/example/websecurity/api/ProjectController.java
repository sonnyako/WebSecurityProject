package com.example.websecurity.api;

import com.example.websecurity.dto.ProjectDto;
import com.example.websecurity.mapper.ProjectMapper;
import com.example.websecurity.security.CurrentUser;
import com.example.websecurity.security.JwtUserDetails;
import com.example.websecurity.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/project")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectService service;

    @PreAuthorize("hasAuthority('ROLE_FREELANCER')")
    @GetMapping("/getAll")
    public List<ProjectDto> getAllProjects() {
        return service.getAll().stream().map(ProjectMapper.INSTANCE::toDto).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    @PostMapping("/create")
    public ProjectDto createProject(@RequestBody @Valid @NotNull(message = "Project info should be present") ProjectDto projectDto) {
        var project = ProjectMapper.INSTANCE.toEntity(projectDto);

        return ProjectMapper.INSTANCE.toDto(service.create(project));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_FREELANCER', 'ROLE_OWNER')")
    @GetMapping("get/by/{id}")
    public ProjectDto getProjectById(@Valid @NotNull @PathVariable("id") Long id) {
        return ProjectMapper.INSTANCE.toDto(service.getById(id));
    }


    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    @DeleteMapping("delete/by/{id}")
    public boolean deleteProjectById(@Valid @NotNull @PathVariable("id") Long id) {
        return service.delete(id);
    }

    @PreAuthorize("hasAuthority('ROLE_FREELANCER')")
    @PostMapping("apply/for/project/{id}")
    public boolean applyForProject(@PathVariable Long id, @CurrentUser @NotNull JwtUserDetails userDetails) {
        var user = userDetails.getUser();
        return service.applyForProject(user, id);
    }
}
