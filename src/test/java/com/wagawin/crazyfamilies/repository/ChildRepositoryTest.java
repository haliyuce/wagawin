package com.wagawin.crazyfamilies.repository;

import com.wagawin.crazyfamilies.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ChildRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void should_get_person_and_the_most_favorite_meal() {
        //given
        final Person person = Person.builder().name("dummyParent").age(25).build();

        List<Child> children = new ArrayList<Child>(){{
           add(Son.builder().name("dummyChild").age(7).parent(person).favoriteMeals(new ArrayList<Meal>(){{
               add(Meal.builder().invented(new Date()).name("dummyMeal1").build());
               add(Meal.builder().invented(new Date()).name("dummyMeal2").build());
           }}).build());
        }};
        person.setChildren(children);
        Person created = this.personRepository.save(person);

        //when
        Child resultChild = this.childRepository.findOne(created.getChildren().get(0).getId());

        //then
        assertEquals(resultChild.getFavoriteMeals().get(0) , created.getChildren().get(0).getFavoriteMeals().get(0));
        assertEquals(resultChild.getFavoriteMeals().size(), 2);
        assertEquals(created , resultChild.getParent());
    }

    @Test
    public void should_get_children_based_on_color() {

        final Person person = Person.builder().name("dummyParent").age(25).build();

        List<Child> children = new ArrayList<Child>(){{
            add(Son.builder().name("dummyChild").parent(person).age(7).favoriteMeals(new ArrayList<Meal>(){{
                add(Meal.builder().invented(new Date()).name("dummyMeal1").build());
                add(Meal.builder().invented(new Date()).name("dummyMeal2").build());
            }}).bicycleColor("red").build());
            add(Daughter.builder().name("daughter").parent(person).age(7).favoriteMeals(new ArrayList<Meal>(){{
                add(Meal.builder().invented(new Date()).name("dummyMeal1").build());
                add(Meal.builder().invented(new Date()).name("dummyMeal2").build());
            }}).hairColor("red").build());
        }};
        person.setChildren(children);
        this.personRepository.save(person);
        //when
        List<Child> resultChild = this.childRepository.findByHairColorOrBicycleColor("red");

        //then
        assertEquals(resultChild.size(), 2);

    }

}