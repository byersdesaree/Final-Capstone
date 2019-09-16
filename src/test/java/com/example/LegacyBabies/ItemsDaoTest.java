package com.example.LegacyBabies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemsDaoTest {
    @Autowired
    ItemsRepository itemsRepo;

    Items items1;
    Items items2;
    Items items3;


    @Before
    public void setUp() {
        itemsRepo.deleteAll();

        items1 = new Items();
        items1.setName("Baby toy");
        items1.setPrice(19.99f);
        items1.setDomestic(true);
        items1.setImported(false);
        items1.setCategory("books");
        items1.setQuantity(13);
        items1.setImageUrl("");

        items2 = new Items();
        items2.setName("Baby food");
        items2.setPrice(15.99f);
        items2.setDomestic(false);
        items2.setImported(true);
        items2.setCategory("food");
        items2.setQuantity(29);
        items2.setImageUrl("");

        items3 = new Items();
        items3.setName("Baby clothes");
        items3.setPrice(50.00f);
        items3.setDomestic(true);
        items3.setImported(false);
        items3.setCategory("food");
        items3.setQuantity(10);
        items3.setImageUrl("");




    }
    @Test
    @Transactional
    public void shouldAddItems() {
        itemsRepo.save(items1);
        itemsRepo.save(items2);


        assertNotNull(items1.getId());
        assertNotNull(items2.getId());
    }

    @Test
    @Transactional
    public void shouldGetItems() {
        itemsRepo.save(items3);
        itemsRepo.save(items2);
        itemsRepo.save(items1);
        List<Items> itemsList=Arrays.asList(items1,items2,items3);

        List<Items> fromRepo = itemsRepo.findAll();

        assertEquals(itemsList, fromRepo);
    }

    @Test
    @Transactional
    public void shouldDeleteItems(){
        itemsRepo.save(items1);

        itemsRepo.deleteById(items1.getId());

        Optional<Items> fromRepo = itemsRepo.findById(items1.getId());

        assertFalse(fromRepo.isPresent());
    }

    @Test
    @Transactional
    public void shouldGetItemsByCategory() {
        itemsRepo.save(items1);

        itemsRepo.save(items2);

        itemsRepo.save(items3);

        List<Items> itemsList = itemsRepo.findByCategory(items1.getCategory());

        assertEquals(1, itemsList.size());

        List<Items> notPresentList = itemsRepo.findByCategory("food");
        assertEquals(2, notPresentList.size());
    }
@After
public void tearDown() {
    itemsRepo.deleteAll();

}
}
