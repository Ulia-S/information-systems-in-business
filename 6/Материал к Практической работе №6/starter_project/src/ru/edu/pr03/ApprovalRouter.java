package ru.edu.pr03;

public class ApprovalRouter {
    public static final long FINANCE_LIMIT = 25L;

    public ApprovalRoute route(long amount, boolean urgent, RiskLevel risk, boolean containsPersonalData) {
        if (amount < 0 || risk == null) {
            return ApprovalRoute.STANDARD;
        }
        if (containsPersonalData) return ApprovalRoute.SECURITY_REVIEW;
        if (risk == RiskLevel.HIGH) return ApprovalRoute.DIRECTOR_REVIEW;
        if (amount >= FINANCE_LIMIT) return ApprovalRoute.FINANCE_REVIEW;
        if (urgent && amount < FINANCE_LIMIT) return ApprovalRoute.FAST_TRACK;
        return ApprovalRoute.STANDARD;
    }
}
