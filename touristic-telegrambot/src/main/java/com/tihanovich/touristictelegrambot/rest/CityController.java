package com.tihanovich.touristictelegrambot.rest;

import com.tihanovich.touristictelegrambot.model.City;
import com.tihanovich.touristictelegrambot.repository.CityRepository;
import com.tihanovich.touristictelegrambot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CityController {

    private final CityService cityService;
    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityService cityService, CityRepository cityRepository) {
        this.cityService = cityService;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/cities")
    public List<City> allCity(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return cityService.readAll(PageRequest.of(pageNum, pageSize)).toList();
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<City> readCity(@PathVariable Long id) {
        if (id == null || !cityRepository.existsById(id)) {
            return new ResponseEntity("City is not found: id = " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cityService.readById(id));
    }

    @PostMapping("/cities")
    public ResponseEntity<City> addCity(@Valid @RequestBody City city) {
        City addedCity;
        try {
            addedCity = cityService.create(city);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(addedCity);
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @Valid @RequestBody City city) {
        if (id == null || !cityRepository.existsById(id)) {
            return new ResponseEntity("City is not found: id = " + id, HttpStatus.NOT_FOUND);
        } else {
            city.setId(id);
            return ResponseEntity.ok().body(cityService.create(city));
        }
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        if (id == null || !cityRepository.existsById(id)) {
            return new ResponseEntity("City is not found: id = " + id, HttpStatus.NOT_FOUND);
        } else {
            cityService.delete(id);
            return new ResponseEntity("City deleted successfully", HttpStatus.OK);
        }
    }
}
