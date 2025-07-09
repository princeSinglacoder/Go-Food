package com.fooddelivery.service.order;

import com.fooddelivery.database.ItemDatabase;
import com.fooddelivery.service.item.model.Item;
import com.fooddelivery.service.order.model.Order;
import com.fooddelivery.service.order.model.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderService {

    @Autowired
    ItemDatabase itemdatabase = new ItemDatabase();

    public OrderResponse calculateTotalPrice(Order order) {
        List<String> orderList= order.getOrderList();

        double price = 0;
        for (String s : orderList) {
            String itemName = s.toLowerCase();
            Item existOrNot = itemdatabase.find(itemName);
            if (existOrNot != null) {
                price += itemdatabase.checkPrice(itemName);
            } else {
                throw new IllegalArgumentException("Item ID not found: " + itemName);
            }
        }

        double total = getTotal(order, price);

        return prepareOrderResponse(total);
    }

    private double getTotal(Order order, double price) {
        int deliveryCharge = 0;
        if (order.isSpecialDays()) {
            deliveryCharge = 50;
        } else if (order.isPeakHour()) {
            deliveryCharge = 30;
        } else if (order.isNightService()) {
            deliveryCharge = 20;
        } else {
            if (price < 500) {
                deliveryCharge = 20;
            }
        }

        // Calculate GST
        double gstPrice = price * 0.05;

        // Final total
        return price + gstPrice + deliveryCharge;
    }

    private OrderResponse prepareOrderResponse(double total) {
        OrderResponse response = new OrderResponse();
        response.setTotalPrice(total);
        return response;
    }
}
