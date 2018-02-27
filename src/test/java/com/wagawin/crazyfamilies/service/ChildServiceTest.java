package com.wagawin.crazyfamilies.service;

import com.wagawin.crazyfamilies.model.*;
import com.wagawin.crazyfamilies.repository.ChildRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ChildServiceTest {

    @InjectMocks
    private ChildService childService;

    @Mock
    private ChildRepository childRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_get_wanted_child_with_parent_and_favorite_meal() {

        //given
        final Person person = Person.builder().name("dummyParent").age(25).build();

        List<Child> children = new ArrayList<Child>(){{
            add(Son.builder().name("dummyChild").age(7).parent(person).favoriteMeals(new ArrayList<Meal>(){{
                add(Meal.builder().invented(new Date()).name("dummyMeal1").build());
                add(Meal.builder().invented(new Date()).name("dummyMeal2").build());
            }}).build());
        }};
        person.setChildren(children);

        Mockito.when(childRepository.findOne(1L)).thenReturn(person.getChildren().get(0));

        //when
        Child c = this.childService.getChildWithFavoriteMealAndParent(1L);

        //then
        assertNotNull(c);
        assertEquals(c.getFavoriteMeals().size(), 1);
        assertNotNull(c.getParent());
    }

}
