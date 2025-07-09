package com.fooddelivery.host.item;

import com.fooddelivery.service.item.model.Item;
import com.fooddelivery.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/item")
public class ItemController {

    @Autowired // Automatically get the existing itemService object
    private ItemService itemService;

    @GetMapping(value = "/{itemName}")
    Item GetItem(@PathVariable String itemName){
        return itemService.GetItem(itemName);
    }

    @PostMapping
    Item PostItem(@RequestBody Item item){
        return itemService.PostItem(item);
    }

    @PutMapping
    String UpdateItem(@RequestBody Item item){
        return itemService.UpdateItem(item);
    }

    @DeleteMapping(value = "/{itemName}")
    String deleteItem(@PathVariable String itemName){
        return itemService.deleteItem(itemName);
    }
}
