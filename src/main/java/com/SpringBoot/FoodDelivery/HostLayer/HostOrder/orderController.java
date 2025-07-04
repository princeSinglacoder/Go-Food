package com.SpringBoot.FoodDelivery.HostLayer.HostOrder;

import com.SpringBoot.FoodDelivery.ServiceLayer.ServiceOrder.Model.orderEntity;
import com.SpringBoot.FoodDelivery.ServiceLayer.ServiceOrder.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class orderController {

    @Autowired
    orderService orderservice = new orderService();

    @PostMapping(value = "api/order")
    String calculateOrderPrice(@RequestBody orderEntity orderentity){
        double total = orderservice.calculateTotalPrice(orderentity);
        return "Total Price: ₹" + total;
    }
}
