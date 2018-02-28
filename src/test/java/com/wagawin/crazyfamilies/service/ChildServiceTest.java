package com.wagawin.crazyfamilies.service;

import com.wagawin.crazyfamilies.model.*;
import com.wagawin.crazyfamilies.repository.ChildRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChildServiceTest {

    @Autowired
    private ChildService childService;

    @MockBean
    private ChildRepository childRepository;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Before
    public void before() {
        Optional.ofNullable(redisCacheManager.getCache("child_fav_meal")).ifPresent(c -> c.clear());
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
        Child c = childService.getChildWithFavoriteMealAndParent(1L);

        //then
        assertNotNull(c);
        assertEquals(c.getFavoriteMeals().size(), 1);
        assertNotNull(c.getParent());
    }

    @Test
    public void should_cache_value_of_child_with_parent_and_favorite_meal() {

        //given
        final Person person = Person.builder().name("dummyParent1").age(25).build();

        List<Child> children = new ArrayList<Child>(){{
            add(Son.builder().name("dummyChild").age(7).parent(person).favoriteMeals(new ArrayList<Meal>(){{
                add(Meal.builder().invented(new Date()).name("dummyMeal1").build());
                add(Meal.builder().invented(new Date()).name("dummyMeal2").build());
            }}).build());
        }};
        person.setChildren(children);

        Mockito.when(childRepository.findOne(1L)).thenReturn(person.getChildren().get(0));

        //when
        Child beforeCache = childService.getChildWithFavoriteMealAndParent(1L);
        Child afterCache = childService.getChildWithFavoriteMealAndParent(1L);

        //then
        assertEquals(beforeCache, afterCache);

        Mockito.verify(childRepository).findOne(1L);
        Mockito.verifyNoMoreInteractions(childRepository);

    }

}
