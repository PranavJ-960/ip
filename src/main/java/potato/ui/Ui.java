package potato.ui;

import java.util.Scanner;

import potato.task.Task;
import potato.tasklist.TaskList;

/**
 * Handles user interface operations such as reading input and displaying messages.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String OUTPUT_SEPARATOR = LINE;
    private static final String OUTPUT_INDENTATION = "  ";

    private final Scanner scanner;

    /**
     * Constructs a {@code Ui} instance and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the logo and welcome message when the application launches.
     */
    public void showWelcome() {
        String logo = " ____   ___ _____  _  _____ ___  \n"
                + "|  _ \\ / _ \\_   _|/ \\|_   _/ _ \\ \n"
                + "| |_) | | | || | / _ \\ | || | | |\n"
                + "|  __/| |_| || |/ ___ \\| || |_| |\n"
                + "|_|    \\___/ |_|/_/   \\_\\_| \\___/\n";
        System.out.println("Hello from\n" + logo);
        showMessages("Hello! I'm Potato.", "What can I do for you?");
    }

    /**
     * Prints a horizontal divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads and returns the next line of input entered by the user.
     *
     * @return Trimmed line of user command text.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays one or more messages framed by separator lines using Java Varargs.
     *
     * @param messages Lines of text to print.
     */
    public void showMessages(String... messages) {
        System.out.println(OUTPUT_SEPARATOR);
        for (String message : messages) {
            System.out.println(OUTPUT_INDENTATION + message);
        }
        System.out.println(OUTPUT_SEPARATOR);
    }

    /**
     * Displays the farewell message upon exiting the application.
     */
    public void showGoodbye() {
        showMessages("Alrighty!! Byebye.", "Hope to see you again soon!");
    }

    /**
     * Displays all tasks currently contained in the task list.
     *
     * @param tasks Task list containing items to display.
     */
    public void showTaskList(TaskList tasks) {
        showLine();
        System.out.println(OUTPUT_INDENTATION + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            try {
                System.out.println(OUTPUT_INDENTATION + (i + 1) + "." + tasks.get(i));
            } catch (Exception ignored) {
            }
        }
        showLine();
    }

    /**
     * Displays notification when a new task has been added.
     *
     * @param task Added task.
     * @param count Total count of tasks remaining in the list.
     */
    public void showTaskAdded(Task task, int count) {
        showMessages(
                "Yessir. I've added this task:",
                "  " + task,
                "Now you have " + count + " tasks in the list."
        );
    }

    /**
     * Displays notification when a task is removed.
     *
     * @param task Removed task.
     * @param count Total count of tasks remaining in the list.
     */
    public void showTaskRemoved(Task task, int count) {
        showMessages(
                "Noted. I've removed this task:",
                "  " + task,
                "Now you have " + count + " tasks in the list."
        );
    }

    /**
     * Displays notification when a task is marked as completed.
     *
     * @param task Completed task.
     */
    public void showTaskMarked(Task task) {
        showMessages(
                "Nice! I've marked this task as done:",
                "  " + task
        );
    }

    /**
     * Displays notification when a task is marked as incomplete.
     *
     * @param task Incomplete task.
     */
    public void showTaskUnmarked(Task task) {
        showMessages(
                "OK, I've marked this task as not done yet:",
                "  " + task
        );
    }

    /**
     * Displays an error message string.
     *
     * @param message Error description to display.
     */
    public void showError(String message) {
        showMessages(message);
    }

    /**
     * Closes the underlying scanner resource.
     */
    public void closeScanner() {
        scanner.close();
    }
}