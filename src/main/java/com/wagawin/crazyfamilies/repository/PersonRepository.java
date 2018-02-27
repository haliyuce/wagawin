package com.wagawin.crazyfamilies.repository;

import com.wagawin.crazyfamilies.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
