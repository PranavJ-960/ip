package potato.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with start and end times or dates.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructs an {@code Event} task with a description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from Start time or date string.
     * @param to End time or date string.
     */
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

    /**
     * Returns the raw start time or date string.
     *
     * @return Raw start string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the raw end time or date string.
     *
     * @return Raw end string.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return Formatted event display string.
     */
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