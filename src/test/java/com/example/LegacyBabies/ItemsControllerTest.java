package com.example.LegacyBabies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemsControllerTest {
    private MockMvc mockMvc;

    @Mock
    ItemsService mockItemsServiceImpl;

    @InjectMocks
    ItemsController itemsController;

    Items items1;
    Items items2;
    Items items3;

    List<Items> itemsList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemsController).build();

        items1 = new Items();
        items1.setName("Baby toy");
        items1.setPrice(19.99f);
        items1.setDomestic(true);
        items1.setImported(false);
        items1.setCategory("books");
        items1.setQuantity(13);
        items1.setId(3);
        items1.setImageUrl("");

        items2 = new Items();
        items2.setName("Baby food");
        items2.setPrice(15.99f);
        items2.setDomestic(false);
        items2.setImported(true);
        items2.setCategory("food");
        items2.setQuantity(29);
        items2.setId(5);
        items2.setImageUrl("");

        items3 = new Items();
        items3.setName("Baby clothes");
        items3.setPrice(50.00f);
        items3.setDomestic(true);
        items3.setImported(false);
        items3.setCategory("food");
        items3.setQuantity(10);
        items3.setId(2);
        items3.setImageUrl("");

        itemsList = Arrays.asList(items1, items2, items3);
    }

    @Test
    public void rootContext_ShouldRespondWith404() throws Exception {


        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void ShouldReturnAllItems() throws Exception {
        when(mockItemsServiceImpl.getAllItems()).thenReturn(itemsList);


        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(itemsList.get(0).getName())));

        verify(mockItemsServiceImpl).getAllItems();

    }

    @Test
    public void ShouldReturnItemsByCategory() throws Exception {
        itemsList = Arrays.asList(items2);
        when(mockItemsServiceImpl.getItemsByCategory(itemsList.get(0).getName())).thenReturn(itemsList);


        mockMvc.perform(get("/items/name/" + itemsList.get(0).getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(itemsList.get(0).getName())));

        verify(mockItemsServiceImpl).getItemsByCategory(itemsList.get(0).getName());

    }

    @Test
    public void ShouldAddItem() throws Exception {
        Items newItems = new Items();
        newItems.setName("It");
        newItems.setPrice(19.99f);
        newItems.setDomestic(true);
        newItems.setImported(false);
        newItems.setCategory("food");
        newItems.setId(2);
        newItems.setQuantity(20);
        newItems.setImageUrl("");



        when(mockItemsServiceImpl.addItem(newItems)).thenReturn(newItems);

        ObjectMapper mapper = new ObjectMapper();
        String objStr = mapper.writeValueAsString(newItems);

        MvcResult result = mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(newItems.getName()))).andReturn();

        // result.getResolvedException().printStackTrace();

        verify(mockItemsServiceImpl).addItem(newItems);
    }

    @Test
    public void ShouldDeleteItems() throws Exception {
        MvcResult result = mockMvc.perform(delete("/items/1"))
                .andExpect(status().isOk()).andReturn();

        // result.getResolvedException().printStackTrace();

        verify(mockItemsServiceImpl).deleteItems(1);
    }

    @Test
    public void ShouldUpdateItems() throws Exception {
        Items newItems = new Items();
        newItems.setName("It");
        newItems.setPrice(19.99f);
        newItems.setDomestic(true);
        newItems.setImported(false);
        newItems.setCategory("food");
        newItems.setId(2);
        newItems.setQuantity(20);
        newItems.setImageUrl("");

        ObjectMapper mapper = new ObjectMapper();
        String objStr = mapper.writeValueAsString(newItems);

        MvcResult result = mockMvc.perform(put("/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objStr))
                .andExpect(status().isOk()).andReturn();

        // result.getResolvedException().printStackTrace();

        verify(mockItemsServiceImpl).updateItems(newItems, 1);
    }
}
