package com.codegym.service;

import com.codegym.model.Country;
import org.springframework.data.domain.Pageable;

public interface CountryService {
    Iterable<Country> findAll(Pageable pageable);

    void save(Country country);

    void remove(Long id);

    Country findById(Long id);
}
