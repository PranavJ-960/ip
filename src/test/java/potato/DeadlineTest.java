package potato.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_validDate_formattedCorrectly() {
        Deadline deadline = new Deadline("return book", "2026-09-15");
        assertEquals("[D][ ] return book (by: Sept 15 2026)", deadline.toString());
    }

    @Test
    public void toString_invalidDateFormat_returnsRawString() {
        Deadline deadline = new Deadline("return book", "Sunday");
        assertEquals("[D][ ] return book (by: Sunday)", deadline.toString());
    }

    @Test
    public void getBy_validInput_returnsByString() {
        Deadline deadline = new Deadline("return book", "2026-09-15");
        assertEquals("2026-09-15", deadline.getBy());
    }
}