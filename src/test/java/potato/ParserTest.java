package potato.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import potato.command.AddCommand;
import potato.command.ExitCommand;
import potato.command.ListCommand;
import potato.exception.PotatoException;

public class ParserTest {

    @Test
    public void parse_todoCommand_success() throws PotatoException {
        assertInstanceOf(AddCommand.class, Parser.parse("todo read book"));
    }

    @Test
    public void parse_byeCommand_success() throws PotatoException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void parse_listCommand_success() throws PotatoException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    @Test
    public void parse_emptyTodoDescription_exceptionThrown() {
        assertThrows(PotatoException.class, () -> Parser.parse("todo "));
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        assertThrows(PotatoException.class, () -> Parser.parse("invalidCommand"));
    }
}