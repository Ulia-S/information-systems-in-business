package ru.edu.pr02;

public class TimesheetAnalyzer {
    // нормы или верхние границы
    public static final int DAILY_NORM = 25;
    public static final int MAX_HOURS = 16;
    public static final int WEEKLY_NORM = 40;
    public static final int DAYS_IN_WEEK = 5;

    public static int[] parseHours(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("Ошибка: пустая строка");
        }

        String[] parts = line.trim().split(";");
        int[] hours = new int[DAYS_IN_WEEK];

        if (parts.length != DAYS_IN_WEEK) {
            throw new IllegalArgumentException("Ошибка: ожидалось " + DAYS_IN_WEEK);
        }

        for (int i = 0; i < parts.length; i++) {
            String token = parts[i].trim();
            int position = i + 1;
            if (token.isEmpty()) {
                throw new IllegalArgumentException("Ошибка: позиция " + position + " пустая");
            }
        
            if (!token.matches("-?\\d+")) {
                throw new IllegalArgumentException(
                    "Ошибка: позиция " + position + " не является целым числом");
            }

            int value = Integer.parseInt(token);
            if (value < 0 || value > MAX_HOURS) {
                    throw new IllegalArgumentException("Ошибка: позиция " + position + " должна быть от 0 до " + MAX_HOURS + ": " + value);
                }
        
            hours[i] = value;
        }

        return hours;
    }

    // суммарное количество часов за неделю
    public static int total(int[] hours) {
        int sum = 0;
        for (int i = 0; i < hours.length; i++) {
            sum += hours[i];
        }
        return sum;
    }

    // среднее количество часов в день
    public static double average(int[] hours) {
        if (hours.length == 0) {
            return 0.0;
        }
        return (double) total(hours) / hours.length;
    }

    // количество дней с переработкой
    public static int overtimeDays(int[] hours) {
        int count = 0;
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > DAILY_NORM) {
                count++;
            }
        }
        return count;
    }

    // недельная переработка
    public static int weeklyOvertime(int[] hours) {
        int diff = total(hours) - WEEKLY_NORM;
        return Math.max(0, diff);
    }

    // итоговый отчёт
    public static String buildReport(int[] hours) {
        return String.format(
            "Сумма: %d ч; среднее: %.2f ч; дней с переработкой: %d; недельная переработка: %d ч",
            total(hours),
            average(hours),
            overtimeDays(hours),
            weeklyOvertime(hours)
        );
    }
}
