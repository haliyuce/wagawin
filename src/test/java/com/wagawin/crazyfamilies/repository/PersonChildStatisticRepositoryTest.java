package com.wagawin.crazyfamilies.repository;

import com.wagawin.crazyfamilies.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonChildStatisticRepositoryTest {

    @Autowired
    private ParentSummaryRepository parentSummaryRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void should_get_valid_data() {

        //given
        final Person person = Person.builder().name("dummyParent").age(25).build();

        List<Child> children = new ArrayList<Child>(){{
            add(Son.builder().name("dummyChild").age(7).parent(person).favoriteMeals(new ArrayList<Meal>(){{
                add(Meal.builder().invented(new Date()).name("dummyMeal1").build());
                add(Meal.builder().invented(new Date()).name("dummyMeal2").build());
            }}).build());

            add(Son.builder().name("dummyChild").age(7).parent(person).favoriteMeals(new ArrayList<Meal>(){{
                add(Meal.builder().invented(new Date()).name("dummyMeal1").build());
                add(Meal.builder().invented(new Date()).name("dummyMeal2").build());
            }}).build());
        }};
        person.setChildren(children);
        this.personRepository.save(person);

        final Person secondPerson = Person.builder().name("dummyParent2").age(25).build();
        this.personRepository.save(secondPerson);

        final Person thirdPerson = Person.builder().name("dummyParent3").age(25).build();

        List<Child> childrenForThird = new ArrayList<Child>(){{
            add(Son.builder().name("dummyChild1").age(7).parent(thirdPerson).favoriteMeals(new ArrayList<Meal>(){{
                add(Meal.builder().invented(new Date()).name("dummyMeal1").build());
                add(Meal.builder().invented(new Date()).name("dummyMeal2").build());
            }}).build());
        }};
        thirdPerson.setChildren(childrenForThird);
        this.personRepository.save(thirdPerson);

        final Person fourthPerson = Person.builder().name("dummyParent4").age(25).build();

        List<Child> childrenForFourth = new ArrayList<Child>(){{
            add(Son.builder().name("dummyChild1").age(7).parent(fourthPerson).favoriteMeals(new ArrayList<Meal>(){{
                add(Meal.builder().invented(new Date()).name("dummyMeal1").build());
                add(Meal.builder().invented(new Date()).name("dummyMeal2").build());
            }}).build());
        }};

        fourthPerson.setChildren(childrenForFourth);
        this.personRepository.save(fourthPerson);

        //when
        List<ParentSummary> actual = this.parentSummaryRepository.getStatisticsOfParentChildCount();

        //then

        assertEquals(actual.size(), 3);

        assertEquals(actual.get(0).getPersonCount() , 1);
        assertEquals(actual.get(0).getChildCount() , 0);

        assertEquals(actual.get(1).getPersonCount() , 2);
        assertEquals(actual.get(1).getChildCount() , 1);

        assertEquals(actual.get(2).getPersonCount() , 1);
        assertEquals(actual.get(2).getChildCount() , 2);
    }

}
