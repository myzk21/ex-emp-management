package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository repository;

    public Administrator login(Administrator administrator) {
        return repository.login(administrator);
    }
}