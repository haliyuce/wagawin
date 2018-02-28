package com.wagawin.crazyfamilies.service;

import com.wagawin.crazyfamilies.model.Child;
import com.wagawin.crazyfamilies.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
HAHAHA , good name indeeed
 */
@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    @Cacheable(value = "child_fav_meal", key = "#childId")
    public Child getChildWithFavoriteMealAndParent(Long childId) {
        Child child =  childRepository.findOne(childId);
        child.setFavoriteMeals(new ArrayList<>(child.getFavoriteMeals().subList(0, 1)));
        child.getParent().setChildren(null);
        return child;
    }

    @Cacheable(value = "color_child", key = "#color")
    public List<Child> findByHairColorOrBicycleColor(String color) {
        return this.childRepository.findByHairColorOrBicycleColor(color);
    }

    @CacheEvict("child_fav_meal")
    public void childRemove(Long childId) {
        this.childRepository.delete(childId);
    }

}
