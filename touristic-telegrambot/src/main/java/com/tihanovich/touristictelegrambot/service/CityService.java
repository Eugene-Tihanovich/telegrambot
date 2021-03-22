package com.tihanovich.touristictelegrambot.service;

import com.tihanovich.touristictelegrambot.model.City;
import com.tihanovich.touristictelegrambot.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City create(City city) {
        return cityRepository.save(city);
    }

    public Page<City> readAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public City readById(Long id) {
        return cityRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("City is not found: id = " + id));
    }

    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    public City findCity(String name) {
        return cityRepository.findByName(name).orElse(new City(1L,"city", "not found"));
    }
}
