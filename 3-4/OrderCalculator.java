package ru.edu.pr01;

public class OrderCalculator {

    // TODO 1 проверка валидности данных
    public static boolean isValid(int quantity, double unitPrice, double discountPercent) {
        public static final double VAT_RATE = 25.0;
        if (quantity < 1 || quantity > 10000) return false;
        if (unitPrice < 1 || unitPrice > 5000000) return false;
        if (discountPercent < 0 || discountPercent > 30) return false;
        return true;
    }

    // TODO 2 базовая сумма = количество * цена
    public static double calculateBase(int quantity, double unitPrice) {
        return quantity * unitPrice;
    }

    // TODO 3 применить скидку
    public static double applyDiscount(double base, double discountPercent) {
        return base * (1 - discountPercent / 100);
    }

    // TODO 4 вычислить НДС
    public static double calculateVat(double discounted, double vatPercent) {
        return discounted * (vatPercent / 100);
    }

    // TODO 5 итоговая сумма (Использовать методы выше)
    public static double calculateTotal(int quantity, double unitPrice, double discountPercent, double vatPercent) {
        double base = calculateBase(quantity, unitPrice);
        double discounted = applyDiscount(base, discountPercent);
        double vat = calculateVat(discounted, vatPercent);
        return discounted + vat;
    }
}