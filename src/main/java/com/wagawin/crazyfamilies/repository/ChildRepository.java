package com.wagawin.crazyfamilies.repository;

import com.wagawin.crazyfamilies.model.Child;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChildRepository extends CrudRepository<Child, Long> {

    @Query(value = "select c from Child c where c.hairColor=?1 or c.bicycleColor = ?1")
    List<Child> findByHairColorOrBicycleColor(String color);
}
