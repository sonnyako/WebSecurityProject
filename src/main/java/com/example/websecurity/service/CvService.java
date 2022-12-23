package com.example.websecurity.service;

import com.example.websecurity.dao.model.Cv;
import com.example.websecurity.dao.model.User;

import java.util.List;

public interface CvService {

    Cv create(Cv cv, User user);

    boolean delete(Long id);

    Cv getById(Long id);

    List<Cv> getAll();
}
