package com.example.websecurity.api;

import com.example.websecurity.dto.CvDto;
import com.example.websecurity.mapper.CvMapper;
import com.example.websecurity.security.CurrentUser;
import com.example.websecurity.security.JwtUserDetails;
import com.example.websecurity.service.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/cv")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CvController {

    private final CvService service;

    @PreAuthorize("hasAuthority('ROLE_FREELANCER')")
    @PostMapping("/create")
    public CvDto createCv(@RequestBody @NotNull CvDto cvDto, @CurrentUser @NotNull JwtUserDetails userDetails) {
        var user = userDetails.getUser();
        var cv = CvMapper.INSTANCE.toEntity(cvDto);

        return CvMapper.INSTANCE.toDto(service.create(cv, user));
    }

    @PreAuthorize("hasAuthority('ROLE_FREELANCER')")
    @DeleteMapping("/delete/by/{id}")
    public boolean deleteCV(@PathVariable("id") Long id) {
        return service.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_FREELANCER','ROLE_OWNER')")
    @GetMapping("/get/by/{id}")
    public CvDto getById(@PathVariable("id") Long id) {
        return CvMapper.INSTANCE.toDto(service.getById(id));
    }

    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    @GetMapping("get/all")
    public List<CvDto> getAll() {
        return service.getAll().stream().map(CvMapper.INSTANCE::toDto).toList();
    }
}
