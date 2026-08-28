package potato.ui;

import java.util.Scanner;
import potato.task.Task;
import potato.tasklist.TaskList;

/**
 * Handles user interface operations such as reading input and displaying messages.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
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
        showLine();
        System.out.println("Hello! I'm Potato.");
        System.out.println("What can I do for you?");
        showLine();
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
     * Displays the farewell message upon exiting the application.
     */
    public void showGoodbye() {
        System.out.println("Alrighty!! Byebye. \nHope to see you again soon!");
    }

    /**
     * Displays all tasks currently contained in the task list.
     *
     * @param tasks Task list containing items to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            try {
                System.out.println((i + 1) + "." + tasks.get(i));
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Displays notification when a new task has been added.
     *
     * @param task Added task.
     * @param count Total count of tasks remaining in the list.
     */
    public void showTaskAdded(Task task, int count) {
        System.out.println("Yessir. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Displays notification when a task is removed.
     *
     * @param task Removed task.
     * @param count Total count of tasks remaining in the list.
     */
    public void showTaskRemoved(Task task, int count) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Displays notification when a task is marked as completed.
     *
     * @param task Completed task.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays notification when a task is marked as incomplete.
     *
     * @param task Incomplete task.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays an error message string.
     *
     * @param message Error description to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Closes the underlying scanner resource.
     */
    public void closeScanner() {
        scanner.close();
    }
}