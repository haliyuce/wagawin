package com.wagawin.crazyfamilies.controller;

import com.wagawin.crazyfamilies.model.dto.ChildDto;
import com.wagawin.crazyfamilies.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("child")
public class ChildController {

    @Autowired
    private ChildService childService;

    @GetMapping("/info/{id]")
    public ChildDto getChildInfo(@PathVariable long id) {
        return childService.getChildWithFavoriteMealAndParent(id).convertToDto();
    }

    @GetMapping("/color/{colorname}")
    public List<ChildDto> getChildrenByColor(@PathVariable String colorname) {
        return this.childService.findByHairColorOrBicycleColor(colorname).stream().map(p -> p.convertToDto()).collect(Collectors.toList());
    }

}
