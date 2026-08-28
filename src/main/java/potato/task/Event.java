package potato.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        try {
            this.fromDate = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            this.fromDate = null;
        }
        try {
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.toDate = null;
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        String displayFrom = (fromDate != null)
                ? fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : from;
        String displayTo = (toDate != null)
                ? toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : to;
        return "[E]" + super.toString() + " (from: " + displayFrom + " to: " + displayTo + ")";
    }
}