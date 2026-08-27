import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;

    // Standard input pattern matching: "2026-09-15 1800" or "15/12/2019 1800"
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.dateTime = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            this.dateTime = null; // Fallback to raw string if format doesn't match
        }
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        String displayDate = (dateTime != null)
                ? dateTime.format(OUTPUT_FORMATTER)
                : by;
        return "[D]" + super.toString() + " (by: " + displayDate + ")";
    }
}