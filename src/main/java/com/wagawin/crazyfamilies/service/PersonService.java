package com.wagawin.crazyfamilies.service;

import com.wagawin.crazyfamilies.model.Person;
import com.wagawin.crazyfamilies.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @CacheEvict(value = "child_fav_meal", key = "person.id")
    public void upsertPerson(Person person) {
        this.personRepository.save(person);
    }

}
