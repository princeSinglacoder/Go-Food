package com.SpringBoot.FoodDelivery.ServiceLayer.ServiceItem;

import com.SpringBoot.FoodDelivery.DatabaseLayer.itemDatabase;
import com.SpringBoot.FoodDelivery.ServiceLayer.ServiceItem.Model.itemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

// So here we write the business logic
@Component
public class itemService {

    @Autowired  // Tells Spring: "inject the existing itemDatabase object here"
    private itemDatabase itemdatabase = new itemDatabase();

    public itemEntity GetItem(String item_name) {
        itemEntity temp= itemdatabase.find(item_name.toLowerCase());
        if(temp!=null){
            return temp;
        }
        throw new NoSuchElementException(item_name+" is not Found");
    }

    public itemEntity PostItem(itemEntity itementity) {
        String item_name = itementity.getItem_name().toLowerCase();
        itementity.setItem_name(item_name);
        return itemdatabase.set(itementity);
    }

    public String UpdateItem(itemEntity itementity) {
        // Check if function is update successfully or not
        String item_name = itementity.getItem_name().toLowerCase();
        itementity.setItem_name(item_name);
        itemEntity check = itemdatabase.update(itementity);
        if(check!=null){
            return "Item Update Successfully";
        }
        return "ItemId Not Found";
    }

    public String deleteItem(String item_name) {
        itemEntity check = itemdatabase.delete(item_name.toLowerCase());
        if(check!=null){
            return "Item Delete Successfully";
        }
        return "ItemId not Found";
    }
}
