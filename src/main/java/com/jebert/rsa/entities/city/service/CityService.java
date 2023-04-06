package com.jebert.rsa.entities.city.service;


import com.jebert.rsa.entities.city.model.City;
import com.jebert.rsa.entities.city.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public CityService() {}

    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    public Page<City> findPage(Integer page, Integer linesPerPage, String direction, String order ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction) , order);
        Page<City> p = cityRepository.findAll(pageRequest);
        return p;
    }

    public Page<City> findCityByName(String name, Integer page, Integer linesPerPage, String direction, String order){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction) , order);
        Page<City> p = cityRepository.findCityByNameIgnoreCase(name, pageRequest);
        return p;
    }

    public Page<City> findCityByNameAndState(String name, String state, Integer page, Integer linesPerPage, String direction, String order){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction) , order);
        Page<City> p = cityRepository.findCityByStateIgnoreCase(name,state, pageRequest);
        return p;
    }

}
