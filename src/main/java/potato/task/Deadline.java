package potato.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {
    protected String rawDeadlineString;
    protected LocalDate parsedDeadlineDate;

    /**
     * Constructs a {@code Deadline} task with a description and due date.
     *
     * @param description Description of the task.
     * @param rawDeadlineString Due date string, optionally in YYYY-MM-DD format.
     */
    public Deadline(String description, String rawDeadlineString) {
        super(description);
        this.rawDeadlineString = rawDeadlineString;
        try {
            this.parsedDeadlineDate = LocalDate.parse(rawDeadlineString);
        } catch (DateTimeParseException e) {
            this.parsedDeadlineDate = null;
        }
    }

    /**
     * Returns the raw due date string.
     *
     * @return Raw deadline string.
     */
    public String getBy() {
        return rawDeadlineString;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return Formatted deadline task string.
     */
    @Override
    public String toString() {
        String formattedDisplayDate = (parsedDeadlineDate != null)
                ? parsedDeadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : rawDeadlineString;
        return "[D]" + super.toString() + " (by: " + formattedDisplayDate + ")";
    }
}