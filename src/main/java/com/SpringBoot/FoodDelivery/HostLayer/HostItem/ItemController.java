package com.SpringBoot.FoodDelivery.HostLayer.HostItem;

import com.SpringBoot.FoodDelivery.ServiceLayer.ServiceItem.Model.itemEntity;
import com.SpringBoot.FoodDelivery.ServiceLayer.ServiceItem.itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/item")
public class ItemController {

    @Autowired // Automatically get the existing itemService object
    private itemService itemservice = new itemService();
    @GetMapping(value = "/{item_name}")
    itemEntity GetItem(@PathVariable String item_name){
        return itemservice.GetItem(item_name);
    }
    @PostMapping
    itemEntity PostItem(@RequestBody itemEntity itementity){
        return itemservice.PostItem(itementity);
    }

    @PutMapping
    String UpdateItem(@RequestBody itemEntity itementity){
        return itemservice.UpdateItem(itementity);
    }

    @DeleteMapping(value = "/{item_name}")
    String deleteItem(@PathVariable String item_name){
        return itemservice.deleteItem(item_name);
    }
}
