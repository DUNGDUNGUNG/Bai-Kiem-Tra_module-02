package com.codegym.service;

import com.codegym.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {
    Page<City>findAll(Pageable pageable);

    void save(City city);

    void remove(Long id);

    City findById(Long id);
}
