package com.spring.apol.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BaseService<T> {

    T save(T entity);

    void delete(T entity);

    Optional<T> findById(Long id);

    List<T> findAll();

}
