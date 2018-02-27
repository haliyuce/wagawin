package com.wagawin.crazyfamilies.controller;

import com.wagawin.crazyfamilies.model.dto.HouseDto;
import com.wagawin.crazyfamilies.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("house")
public class HouseController {

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping("/person/{id}")
    public HouseDto getHouseByPerson(@PathVariable long id) {
        return this.houseRepository.findByPersonId(id).convertToDto();
    }

}