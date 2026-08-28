package potato.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for testing the behavior of the {@link Deadline} class.
 */
public class DeadlineTest {

    /**
     * Tests that string representation formats a valid ISO date into 'MMM dd yyyy'.
     */
    @Test
    public void toString_validDate_formattedCorrectly() {
        Deadline deadline = new Deadline("return book", "2026-09-15");
        assertEquals("[D][ ] return book (by: Sept 15 2026)", deadline.toString());
    }

    /**
     * Tests that string representation falls back to raw string when input date is non-ISO format.
     */
    @Test
    public void toString_invalidDateFormat_returnsRawString() {
        Deadline deadline = new Deadline("return book", "Sunday");
        assertEquals("[D][ ] return book (by: Sunday)", deadline.toString());
    }

    /**
     * Tests that {@code getBy()} returns the exact raw deadline string passed to the constructor.
     */
    @Test
    public void getBy_validInput_returnsByString() {
        Deadline deadline = new Deadline("return book", "2026-09-15");
        assertEquals("2026-09-15", deadline.getBy());
    }
}