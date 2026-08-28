public class Parser {
    private enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN;

        public static CommandType parse(String word) {
            try {
                return CommandType.valueOf(word.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

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
            default:
                throw new PotatoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

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