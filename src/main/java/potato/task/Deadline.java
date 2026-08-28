package potato.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.date = null;
        }
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        String displayDate = (date != null)
                ? date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : by;
        return "[D]" + super.toString() + " (by: " + displayDate + ")";
    }
}