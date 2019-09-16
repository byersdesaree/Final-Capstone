package com.example.LegacyBabies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.List;

@Component
public class ItemsService {
    @Autowired
    private ItemsRepository itemsRepo;

    public Items addItem(Items item) {
        itemsRepo.save(item);
        return item;
    }

    public List<Items> getAllItems() {
        return itemsRepo.findAll();
    }


    public Items getItemById(int id) {
        return itemsRepo.getOne(id);
    }

    public Items updateItems(Items item, int id) {
        itemsRepo.save(item);
        return item;
    }

    public void deleteItems(int id) {
        itemsRepo.deleteById(id);
    }

    public static Float roundToDecimal (Float d) {
        return Math.round(d /.5f) *.5f;
    }


    public Items  purchaseItem(Items items ) {

        String itemType = items.getCategory();
        Integer itemId = items.getId(),
                quantity = items.getQuantity();
        Float itemPrice = items.getPrice(),
                subtotal = itemPrice * quantity,
                taxRate = this.getSalesTaxByCategory(items),
                importFee = this.getImportFeeByCategory(items),

                tax = taxRate * subtotal,
                importTax = importFee * subtotal,
                total = subtotal + tax + importTax;


        items.setPrice(itemPrice);
        items.setTotal(total);
        items.setSalesTax(taxRate);
        items.setImportFee(importFee);

//        this.updateItemQuantity(itemType, itemId, quantity);
//
//
//        itemsRepo.save(items);
        return items;

    }

    public Float getSalesTaxByCategory(Items items) {

        float salesTax = .10f;
        if (items.getCategory() == "music" || items.getCategory() == "luxury items" || items.getCategory() == "clothes ") {
            return salesTax * (items.getPrice() * items.getQuantity()) ;
        } else{
            return 0.0f;
        }

    }

    public Float getImportFeeByCategory(Items items) {
        float importTax = .05f;
        float noTax = 0.0f;
        if (items.getImported() == true) {
            return importTax;
        } else {
            return noTax;
        }
    }

    public void updateItemQuantity(String type, int id, int quantity) {
        Items items = itemsRepo.getOne(id);
        items.setQuantity(items.getQuantity() - quantity);

    }

    public List<Items> getItemsByCategory(String category) {

        return itemsRepo.findByCategory(category);
    }






}
