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

    public void upsertPerson(Person person) {
        this.personRepository.save(person);
    }

}
