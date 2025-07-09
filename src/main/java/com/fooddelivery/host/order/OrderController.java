package com.fooddelivery.host.order;

import com.fooddelivery.service.order.model.OrderResponse;
import com.fooddelivery.service.order.model.Order;
import com.fooddelivery.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService = new OrderService();

    @PostMapping(value = "api/order")
    OrderResponse calculateOrderPrice(@RequestBody Order order){
        return orderService.calculateTotalPrice(order);
    }
}
