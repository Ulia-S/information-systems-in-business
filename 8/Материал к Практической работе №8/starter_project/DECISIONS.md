# DECISIONS

## 1. Убрать setStatus, оставить доменные методы
Открытый setter позволяет любые переходы (DRAFT -> COMPLETED, REJECTED -> APPROVED).
submit, approve, reject, cancel, complete, каждая проверяет ожидаемый статус через requireStatus()
и бросает IllegalStateException без изменения объекта.

## 2. Минимальная длина причины в BusinessTrip
Константа MIN_REASON_LENGTH = 25 в BusinessTrip.
Проверка в reject(String): reason != null && reason.length() >= 25, иначе IllegalArgumentException.
Границы: 24 -> ошибка, 25 -> OK. Причина хранится в поле rejectionReason только при успехе.

## Таблица переходов
DRAFT       -> SUBMITTED (submit), CANCELLED (cancel)
SUBMITTED   -> APPROVED (approve), REJECTED (reject), CANCELLED (cancel)
APPROVED    -> COMPLETED (complete, today > end), CANCELLED (cancel)
REJECTED    -> (конечное)
CANCELLED   -> (конечное)
COMPLETED   -> (конечное)