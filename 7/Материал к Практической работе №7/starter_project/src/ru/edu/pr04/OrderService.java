package ru.edu.pr04;

public class OrderService {
    public Order createOrder(Employee e) { return new Order(e); }

    public void validateForConfirm(Order order) {
        if (order == null) throw new IllegalArgumentException("order");
        if (order.getItems().isEmpty()) throw new IllegalStateException("empty");
    }
}
