package potato.parser;

import potato.command.*;
import potato.exception.PotatoException;
import potato.task.Deadline;
import potato.task.Event;
import potato.task.Todo;

/**
 * Parses user input strings into executable {@code Command} objects.
 */
public class Parser {

    /**
     * Enumerates supported command types and provides parsing from input strings.
     */
    private enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, UNKNOWN;

        /**
         * Parses a raw word into a corresponding {@code CommandType}.
         *
         * @param word Command word to parse.
         * @return Matched {@code CommandType}, or {@code UNKNOWN} if invalid.
         */
        public static CommandType parse(String word) {
            try {
                return CommandType.valueOf(word.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

    /**
     * Parses the full command string entered by the user into an executable {@code Command}.
     *
     * @param fullCommand Entire line of user input.
     * @return Corresponding {@code Command} object.
     * @throws PotatoException If input is unknown or required arguments are missing/malformed.
     */
    public static Command parse(String fullCommand) throws PotatoException {
        String[] words = fullCommand.trim().split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1].trim() : "";

        CommandType type = CommandType.parse(commandWord);

        switch (type) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(parseIndex(arguments, "mark"), true);
            case UNMARK:
                return new MarkCommand(parseIndex(arguments, "unmark"), false);
            case DELETE:
                return new DeleteCommand(parseIndex(arguments, "delete"));
            case TODO:
                if (arguments.isEmpty()) {
                    throw new PotatoException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new AddCommand(new Todo(arguments));
            case DEADLINE:
                return parseDeadline(arguments);
            case EVENT:
                return parseEvent(arguments);
            case FIND:
                if (arguments.isEmpty()) {
                    throw new PotatoException("OOPS!!! The description of a find command cannot be empty.");
                }
                return new FindCommand(arguments);
            default:
                throw new PotatoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the zero-based task index from string arguments for index-based commands.
     *
     * @param arg String containing the index argument.
     * @param command Name of the command requesting index parsing.
     * @return Zero-based integer index.
     * @throws PotatoException If index string is empty or invalid.
     */
    private static int parseIndex(String arg, String command) throws PotatoException {
        if (arg.isEmpty()) {
            throw new PotatoException("OOPS!!! Please provide a task number to " + command + ".");
        }
        try {
            return Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new PotatoException("OOPS!!! Please specify a valid integer task number.");
        }
    }

    /**
     * Parses arguments for creating a {@code Deadline} task command.
     *
     * @param arguments Description and /by arguments.
     * @return Constructed {@code AddCommand} containing a {@code Deadline}.
     * @throws PotatoException If description or deadline target date is missing.
     */
    private static Command parseDeadline(String arguments) throws PotatoException {
        if (arguments.isEmpty()) {
            throw new PotatoException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new PotatoException("OOPS!!! Please specify a deadline using '/by <date/time>'.");
        }
        return new AddCommand(new Deadline(parts[0].trim(), parts[1].trim()));
    }

    /**
     * Parses arguments for creating an {@code Event} task command.
     *
     * @param arguments Description, /from, and /to arguments.
     * @return Constructed {@code AddCommand} containing an {@code Event}.
     * @throws PotatoException If description, start time, or end time is missing.
     */
    private static Command parseEvent(String arguments) throws PotatoException {
        if (arguments.isEmpty()) {
            throw new PotatoException("OOPS!!! The description of an event cannot be empty.");
        }
        String[] parts = arguments.split(" /from ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new PotatoException("OOPS!!! Please specify event timing using '/from <start> /to <end>'.");
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new PotatoException("OOPS!!! Please specify event end time using '/to <end>'.");
        }
        return new AddCommand(new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
    }
}