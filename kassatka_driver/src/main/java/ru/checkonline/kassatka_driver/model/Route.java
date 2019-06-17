package ru.checkonline.kassatka_driver.model;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.checkonline.kassatka_driver.DriverConstants;

public class Route {

    private String name;
    private String price;
    private int count;

    private Route(String name, String price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Nullable
    public static Route newInstance(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String name = bundle.getString(DriverConstants.EXTRA_NAME);
        String price = bundle.getString(DriverConstants.EXTRA_PRICE);
        int count = bundle.getInt(DriverConstants.EXTRA_COUNT);

        if (name == null || price == null) {
            throw new NullPointerException("Parameters is null");
        }

        return new Route(name, price, count);
    }
}
