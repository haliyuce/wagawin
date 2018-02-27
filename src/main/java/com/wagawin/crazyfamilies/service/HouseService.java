package com.wagawin.crazyfamilies.service;

import com.wagawin.crazyfamilies.model.House;
import com.wagawin.crazyfamilies.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Cacheable("housebyperson")
    public House getHouse(Long personId) {
        return this.houseRepository.findByPersonId(personId);
    }

}
