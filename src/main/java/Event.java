import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_ONLY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter OUTPUT_DATE_ONLY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String input) {
        // 1. Try parsing full date + time: "2026-09-15 1400"
        try {
            return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e1) {
            // 2. Try parsing date only: "2026-09-15" (defaults time to 00:00)
            try {
                return LocalDate.parse(input, DATE_ONLY_FORMATTER).atStartOfDay();
            } catch (DateTimeParseException e2) {
                // 3. Fallback to null for raw text inputs like "Aug 6th 2pm"
                return null;
            }
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    private String formatOutput(LocalDateTime dateTime, String rawString) {
        if (dateTime == null) {
            return rawString;
        }
        // If time is midnight (00:00), format as date only; otherwise format with time
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            return dateTime.format(OUTPUT_DATE_ONLY_FORMATTER);
        }
        return dateTime.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatOutput(fromDateTime, from)
                + " to: " + formatOutput(toDateTime, to) + ")";
    }
}