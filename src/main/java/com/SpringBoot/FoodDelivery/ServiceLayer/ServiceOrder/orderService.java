package com.SpringBoot.FoodDelivery.ServiceLayer.ServiceOrder;

import com.SpringBoot.FoodDelivery.DatabaseLayer.itemDatabase;
import com.SpringBoot.FoodDelivery.ServiceLayer.ServiceItem.Model.itemEntity;
import com.SpringBoot.FoodDelivery.ServiceLayer.ServiceOrder.Model.orderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class orderService {

    @Autowired
    itemDatabase itemdatabase = new itemDatabase();
    public double calculateTotalPrice(orderEntity orderentity) {
        List<String> orderList= orderentity.getOrderList();
        double price = 0;
        for(int i=0;i<orderList.size();i++){
            String itemName = orderList.get(i).toLowerCase();
            itemEntity existOrNot = itemdatabase.find(itemName);
            if(existOrNot!=null){
                price+=itemdatabase.checkPrice(itemName);
            }
            else{
                throw new IllegalArgumentException("Item ID not found: " + itemName);
            }
        }
        int deliveryCharge = 0;
        if (orderentity.isSpecialDays()) {
            deliveryCharge = 50;
        } else if (orderentity.isPeakHour()) {
            deliveryCharge = 30;
        } else if (orderentity.isNightService()) {
            deliveryCharge = 20;
        } else {
            if (price < 500) {
                deliveryCharge = 20;
            }
        }
        // Calculate GST
        double gstPrice = price * 0.05;

        // Final total
        double total = price + gstPrice + deliveryCharge;

        return total;
    }
}
