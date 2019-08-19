package com.codegym.service;

import com.codegym.model.Country;

public interface CountryService {
    Iterable<Country> findAll();

    void save(Country country);

    void remove(Long id);

    Country findById(Long id);
}
