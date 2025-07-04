package com.SpringBoot.FoodDelivery.ServiceLayer.ServiceOrder.Model;

import java.util.ArrayList;
import java.util.List;

public class orderEntity {
    private String name;
    private List<String> orderList = new ArrayList<>();
    private boolean peakHour;
    private boolean specialDays;
    private boolean nightService;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }

    public boolean isPeakHour() {
        return peakHour;
    }

    public void setPeakHour(boolean peakHour) {
        this.peakHour = peakHour;
    }

    public boolean isSpecialDays() {
        return specialDays;
    }

    public void setSpecialDays(boolean specialDays) {
        this.specialDays = specialDays;
    }

    public boolean isNightService() {
        return nightService;
    }

    public void setNightService(boolean nightService) {
        this.nightService = nightService;
    }
}
