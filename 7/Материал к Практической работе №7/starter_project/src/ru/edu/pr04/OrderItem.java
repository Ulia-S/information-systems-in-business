package ru.edu.pr04;

public class OrderItem {
    public static final int MAX_QUANTITY = 25;

    private final Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("product");
        this.product = product;
        setQuantity(quantity);
    }

    private void setQuantity(int quantity) {
        if (quantity < 1 || quantity > MAX_QUANTITY)
            throw new IllegalArgumentException("quantity");
        this.quantity = quantity;
    }

    public void increase(int delta) {
        if (delta <= 0) throw new IllegalArgumentException("delta");
        setQuantity(this.quantity + delta);
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getLineTotal() { return product.getPrice() * quantity; }
}
