package potato.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Constructs a {@code Deadline} task with a description and due date.
     *
     * @param description Description of the task.
     * @param by Due date string, optionally in YYYY-MM-DD format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.date = null;
        }
    }

    /**
     * Returns the raw due date string.
     *
     * @return Raw deadline string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return Formatted deadline task string.
     */
    @Override
    public String toString() {
        String displayDate = (date != null)
                ? date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : by;
        return "[D]" + super.toString() + " (by: " + displayDate + ")";
    }
}