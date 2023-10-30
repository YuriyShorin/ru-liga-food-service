package ru.liga.util;

import ru.liga.model.Item;
import java.util.List;

public class PaymentCalculator {

    public static Double calculate(List<Item> items) {
        double payment = 0.0;
        for (Item item : items) {
            payment += item.getPrice() * item.getQuantity();
        }
        return payment;
    }
}
