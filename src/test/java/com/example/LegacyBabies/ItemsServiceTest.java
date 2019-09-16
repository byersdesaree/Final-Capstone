package com.example.LegacyBabies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ItemsServiceTest {
    @Mock
    @Autowired
    ItemsRepository itemsRepoMock;

    @InjectMocks
    ItemsService itemsService;

    Items items1;
    Items items2;
    Items items3;

    List<Items> itemsList;


@Before
public void setup() {
   // itemsService= new ItemsService();

    items1 = new Items();
    items1.setName("Baby book");
    items1.setPrice(12.49f);
    items1.setDomestic(true);
    items1.setImported(false);
    items1.setCategory("books");
    items1.setQuantity(13);
    items1.setId(3);
    items1.setImageUrl("");

    items2 = new Items();
    items2.setName("Baby cd");
    items2.setPrice(14.99f);
    items2.setDomestic(false);
    items2.setImported(true);
    items2.setCategory("music");
    items2.setQuantity(29);
    items2.setId(5);
    items2.setImageUrl("");

    items3 = new Items();
    items3.setName("Baby chocolate");
    items3.setPrice(0.85f);
    items3.setDomestic(true);
    items3.setImported(false);
    items3.setCategory("food");
    items3.setQuantity(10);
    items3.setId(2);
    items3.setImageUrl("");



    itemsList = Arrays.asList(items1, items2, items3);
}

    @Test
    public void shouldGetAllItems(){
        when(itemsRepoMock.findAll()).thenReturn(itemsList);
        assertEquals(itemsList, itemsService.getAllItems());
    }


    @Test
    public void shouldGetItemsByCategory() {
    List<Items> itemsList = Arrays.asList(items1);
        when(itemsRepoMock.findByCategory("book")).thenReturn(itemsList);
        assertEquals(items1, itemsService.getItemsByCategory("book"));
    }
    @Test
    public void shouldAddItems() {
        when(itemsRepoMock.save(items1)).thenReturn(items1);
        assertEquals(items1, itemsService.addItem(items1));
    }

    @Test
    public void shouldUpdateItems() {
        when(itemsRepoMock.save(items1)).thenReturn(items1);
        assertEquals(items1, itemsService.updateItems(items1, 1));
    }

    @Test
    public void testPurchaseItem(){
    when(itemsRepoMock.getOne(items1.getId())).thenReturn(items1);
        when(itemsRepoMock.getOne(items2.getId())).thenReturn(items2);
        when(itemsRepoMock.getOne(items3.getId())).thenReturn(items3);

    assertEquals(162.399f, itemsService.purchaseItem(items1).getTotal(),0.0);
    assertEquals(499.91f, itemsService.purchaseItem(items2).getTotal(),0.0);
   assertEquals(8.50f,itemsService.purchaseItem(items3).getTotal(),0.0);//
    }

    @Test
    public void shouldGetSalesTaxByCategory(){
    assertEquals(0.0f, itemsService.getSalesTaxByCategory(items1),0.0);
    assertEquals(1.499f, itemsService.getSalesTaxByCategory(items2),0.0);
    assertEquals(0.0f, itemsService.getSalesTaxByCategory(items3),0.0);
    }

    @Test
    public void shouldGetImportFeeByCategory(){
    assertEquals(0.0f, itemsService.getImportFeeByCategory(items1),0.0);
    assertEquals(0.74f, itemsService.getImportFeeByCategory(items2),0.0);
    assertEquals(0.0f, itemsService.getImportFeeByCategory(items3),0.0);

    }






}
