package ru.edu.pr05;

import java.time.LocalDate;
import java.util.Objects;

public class BusinessTrip {
    public static final int MIN_REASON_LENGTH = 25;

    private final String id;
    private final LocalDate start;
    private final LocalDate end;
    private TripStatus status = TripStatus.DRAFT;
    private String rejectionReason;

    public BusinessTrip(String id, LocalDate start, LocalDate end) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id");
        Objects.requireNonNull(start, "start");
        Objects.requireNonNull(end, "end");
        if (start.isAfter(end)) throw new IllegalArgumentException("start > end");
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public void submit() {
        requireStatus(TripStatus.DRAFT);
        status = TripStatus.SUBMITTED;
    }

    public void approve() {
        requireStatus(TripStatus.SUBMITTED);
        status = TripStatus.APPROVED;
    }

    public void reject(String reason) {
        requireStatus(TripStatus.SUBMITTED);
        if (reason == null || reason.length() < MIN_REASON_LENGTH)
            throw new IllegalArgumentException("reason too short");
        this.rejectionReason = reason;
        status = TripStatus.REJECTED;
    }

    public void cancel() {
        if (status != TripStatus.DRAFT
                && status != TripStatus.SUBMITTED
                && status != TripStatus.APPROVED)
            throw new IllegalStateException("cancel not allowed from " + status);
        status = TripStatus.CANCELLED;
    }

    public void complete(LocalDate today) {
        requireStatus(TripStatus.APPROVED);
        if (today == null) throw new IllegalArgumentException("today");
        if (!today.isAfter(end))
            throw new IllegalStateException("trip not finished yet");
        status = TripStatus.COMPLETED;
    }

    private void requireStatus(TripStatus expected) {
        if (status != expected)
            throw new IllegalStateException("expected " + expected + " but was " + status);
    }

    public TripStatus getStatus() { return status; }
    public String getRejectionReason() { return rejectionReason; }
}
