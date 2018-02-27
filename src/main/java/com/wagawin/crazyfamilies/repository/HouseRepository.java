package com.wagawin.crazyfamilies.repository;

import com.wagawin.crazyfamilies.model.House;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepository extends CrudRepository<House, Long> {

    House findByPersonId(Long parentId);

}
