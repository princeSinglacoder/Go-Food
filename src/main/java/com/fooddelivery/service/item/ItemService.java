package com.fooddelivery.service.item;

import com.fooddelivery.database.ItemDatabase;
import com.fooddelivery.service.item.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

// So here we write the business logic
@Component
public class ItemService {

    @Autowired  // Tells Spring: "inject the existing itemDatabase object here"
    private ItemDatabase itemDatabase;

    public Item GetItem(String item_name) {
        Item temp= itemDatabase.find(item_name.toLowerCase());
        if(temp!=null){
            return temp;
        }
        throw new NoSuchElementException(item_name+" is not Found");
    }

    public Item PostItem(Item item) {
        String item_name = item.getItemName().toLowerCase();
        item.setItemName(item_name);
        return itemDatabase.set(item);
    }

    public String UpdateItem(Item itementity) {
        // Check if function is update successfully or not
        String item_name = itementity.getItemName().toLowerCase();
        itementity.setItemName(item_name);
        Item check = itemDatabase.update(itementity);
        if(check!=null){
            return "Item Update Successfully";
        }
        return "ItemId Not Found";
    }

    public String deleteItem(String item_name) {
        Item check = itemDatabase.delete(item_name.toLowerCase());
        if(check!=null){
            return "Item Delete Successfully";
        }
        return "ItemId not Found";
    }
}
