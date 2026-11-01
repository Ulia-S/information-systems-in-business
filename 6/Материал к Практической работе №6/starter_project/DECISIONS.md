# DECISIONS

## Решение 1: порядок проверок в route
Сначала проверяем personalData -> SECURITY_REVIEW.
Потом risk == HIGH -> DIRECTOR_REVIEW.
Потом amount >= FINANCE_LIMIT -> FINANCE_REVIEW.
Потом urgent && amount < FINANCE_LIMIT -> FAST_TRACK.
Иначе STANDARD.
Более строгое правило не должно перекрываться более слабым.
Например, заявка с персональными данными всё равно идёт в SECURITY_REVIEW.

## Решение 2: порог в одном месте
FINANCE_LIMIT = 25L хранится только в ApprovalRouter.
Проверка включения: amount >= FINANCE_LIMIT
При изменении требования меняем одну константу, а не много мест.