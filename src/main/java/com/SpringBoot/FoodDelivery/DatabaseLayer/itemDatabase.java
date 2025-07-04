package com.SpringBoot.FoodDelivery.DatabaseLayer;

import com.SpringBoot.FoodDelivery.ServiceLayer.ServiceItem.Model.itemEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // tells spring to create ONE Object of this class and manage it
public class itemDatabase {
    private Map<String, itemEntity> storeItemData = new HashMap<>();
    public itemEntity find(String item_name) {
        return storeItemData.get(item_name);
    }

    public itemEntity set(itemEntity itementity) {
        if(!storeItemData.containsKey(itementity.getItem_name())){
            storeItemData.put(itementity.getItem_name(),itementity);
            return itementity;
        }
        throw new IllegalArgumentException("Item already exists: " + itementity.getItem_name());
    }

    public itemEntity update(itemEntity itementity) {
        String item_name=itementity.getItem_name();
        if(storeItemData.containsKey(item_name)){
            storeItemData.put(item_name,itementity);
            return itementity;
        }
        return null;
    }

    public itemEntity delete(String item_name) {
        return storeItemData.remove(item_name);
    }

    public int checkPrice(String item) {
        return storeItemData.get(item).getItem_price();
    }
}
