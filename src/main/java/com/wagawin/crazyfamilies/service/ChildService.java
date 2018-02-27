package com.wagawin.crazyfamilies.service;

import com.wagawin.crazyfamilies.model.Child;
import com.wagawin.crazyfamilies.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
HAHAHA , good name indeeed
 */
@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    @Cacheable("child_fav_meal")
    public Child getChildWithFavoriteMealAndParent(Long childId) {
        Child child =  this.childRepository.findOne(childId);
        child.setFavoriteMeals(child.getFavoriteMeals().subList(0, 1));
        return child;
    }

    public List<Child> findByHairColorOrBicycleColor(String color) {
        return this.childRepository.findByHairColorOrBicycleColor(color);
    }

}
