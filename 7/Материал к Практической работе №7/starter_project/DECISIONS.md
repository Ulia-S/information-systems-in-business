# DECISIONS

## 1. Объединение повторной позиции в Order.addProduct
Ищем товар по id. Если найден — вызываем increase(quantity) у существующей OrderItem.
Иначе создаём новую. Правило "один товар — одна строка" относится к заказу, поэтому логика в Order.

## 2. Неизменяемость после confirm
Все изменения (addProduct, removeProduct) сначала вызывают checkDraft(), который кидает IllegalStateException если status == CONFIRMED.
getItems() возвращает List.copyOf(items), чтобы внешний код не мог менять коллекцию. Почему: инкапсуляция + защита правила.

## Вариант 4
MAX_QUANTITY = 25 в OrderItem. Проверка 1 <= quantity <= 25 в setQuantity.