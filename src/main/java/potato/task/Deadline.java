package potato.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String rawDeadlineString;
    protected LocalDate parsedDeadlineDate;

    public Deadline(String description, String rawDeadlineString) {
        super(description);
        this.rawDeadlineString = rawDeadlineString;
        try {
            this.parsedDeadlineDate = LocalDate.parse(rawDeadlineString);
        } catch (DateTimeParseException e) {
            this.parsedDeadlineDate = null;
        }
    }

    public String getBy() {
        return rawDeadlineString;
    }

    @Override
    public String toString() {
        String formattedDisplayDate = (parsedDeadlineDate != null)
                ? parsedDeadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : rawDeadlineString;
        return "[D]" + super.toString() + " (by: " + formattedDisplayDate + ")";
    }
}