public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    UNKNOWN;

    public static Command parse(String input) {
        String firstWord = input.split(" ", 2)[0].toUpperCase();
        try {
            return Command.valueOf(firstWord);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
