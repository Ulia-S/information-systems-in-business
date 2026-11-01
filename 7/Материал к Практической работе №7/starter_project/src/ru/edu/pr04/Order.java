package ru.edu.pr04;

import java.util.*;

public class Order {
    private final Employee employee;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.DRAFT;

    public Order(Employee employee) {
        this.employee = Objects.requireNonNull(employee);
    }

    public void addProduct(Product product, int quantity) {
        checkDraft();
        if (product == null) throw new IllegalArgumentException("product");
        for (OrderItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.increase(quantity);
                return;
            }
        }
        items.add(new OrderItem(product, quantity));
    }

    public void removeProduct(String productId) {
        checkDraft();
        items.removeIf(i -> i.getProduct().getId().equals(productId));
    }

    public double total() {
        double sum = 0;
        for (OrderItem item : items) sum += item.getLineTotal();
        return sum;
    }

    public void confirm() {
        if (status == OrderStatus.CONFIRMED) throw new IllegalStateException("already confirmed");
        if (items.isEmpty()) throw new IllegalStateException("empty order");
        status = OrderStatus.CONFIRMED;
    }

    private void checkDraft() {
        if (status == OrderStatus.CONFIRMED)
            throw new IllegalStateException("order confirmed");
    }

    public List<OrderItem> getItems() { return List.copyOf(items); }
    public OrderStatus getStatus() { return status; }
}
