package com.wagawin.crazyfamilies.repository;

import com.wagawin.crazyfamilies.model.House;
import com.wagawin.crazyfamilies.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HouseRepositoryTest {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void should_get_relevant_houses_for_person() {

        //given
        Person person = new Person();

        person = this.personRepository.save(person);

        House house = new House();
        house.setPerson(person);

        house = this.houseRepository.save(house);

        //when
        House returnHouse = this.houseRepository.findByPersonId(house.getPerson().getId());

        //then
        assertEquals(returnHouse, house);

    }

}
