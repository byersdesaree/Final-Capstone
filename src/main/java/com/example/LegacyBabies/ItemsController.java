package com.example.LegacyBabies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ItemsController {

    @Autowired
    ItemsService service;

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<Items> getAllItems() {
        return service.getAllItems();
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    public Items getItemsById(@PathVariable int id) {
        return service.getItemById(id);
    }


    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public Items  getPurchase(@RequestBody Items items){return service.purchaseItem(items);}

    @RequestMapping(value = "/salesTax", method = RequestMethod.POST)
    public Float getSalesTax(@RequestBody Items items){return service.getSalesTaxByCategory(items);}

    @RequestMapping(value = "/importTax", method = RequestMethod.POST)
    public Float getImportTax(@RequestBody Items items){return service.getImportFeeByCategory(items);}

    @RequestMapping(value="/items/{itemCategory}", method=RequestMethod.GET)
        public List<Items> getItemsByCategory(@PathVariable String itemCategory) {
        return service.getItemsByCategory(itemCategory);
    }
    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public Items createItem(@RequestBody Items item) {
        service.addItem(item);
        return item;
    }

    @RequestMapping(value = "/items/{itemName}", method = RequestMethod.PUT)
    public void updateItem(@RequestBody Items item, @PathVariable int itemName) {
        service.updateItems(item, itemName);
    }

    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable Integer itemId) {
        service.deleteItems(itemId);
    }



}
