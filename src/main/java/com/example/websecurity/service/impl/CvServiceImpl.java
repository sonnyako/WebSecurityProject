package com.example.websecurity.service.impl;

import com.example.websecurity.dao.model.Cv;
import com.example.websecurity.dao.model.User;
import com.example.websecurity.dao.repository.CvRepository;
import com.example.websecurity.service.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CvServiceImpl implements CvService {

    private final CvRepository repository;

    @Override
    public Cv create(Cv cv, User user) {
        return repository.save(Cv.builder()
                .description(cv.getDescription())
                .user(user)
                .build());
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Cv getById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Cv> getAll() {
        return repository.findAll();
    }
}
