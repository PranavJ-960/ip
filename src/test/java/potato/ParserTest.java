package potato.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import potato.command.AddCommand;
import potato.command.ExitCommand;
import potato.command.ListCommand;
import potato.exception.PotatoException;

/**
 * Contains unit tests for testing input parsing in the {@link Parser} class.
 */
public class ParserTest {

    /**
     * Tests that a valid 'todo' command string parses into an {@code AddCommand}.
     *
     * @throws PotatoException If parsing fails unexpectedly.
     */
    @Test
    public void parseTodoCommandSuccess() throws PotatoException {
        assertInstanceOf(AddCommand.class, Parser.parse("todo read book"));
    }

    /**
     * Tests that 'bye' parses into an {@code ExitCommand}.
     *
     * @throws PotatoException If parsing fails unexpectedly.
     */
    @Test
    public void parseByeCommandSuccess() throws PotatoException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    /**
     * Tests that 'list' parses into a {@code ListCommand}.
     *
     * @throws PotatoException If parsing fails unexpectedly.
     */
    @Test
    public void parseListCommandSuccess() throws PotatoException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    /**
     * Tests that entering 'todo' with no description throws a {@code PotatoException}.
     */
    @Test
    public void parseEmptyTodoDescriptionExceptionThrown() {
        assertThrows(PotatoException.class, () -> Parser.parse("todo "));
    }

    /**
     * Tests that an unrecognized command word throws a {@code PotatoException}.
     */
    @Test
    public void parseUnknownCommandExceptionThrown() {
        assertThrows(PotatoException.class, () -> Parser.parse("invalidCommand"));
    }
}