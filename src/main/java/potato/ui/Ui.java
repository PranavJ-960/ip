package potato.ui;

import java.util.Scanner;

import potato.exception.PotatoException;
import potato.task.Task;
import potato.tasklist.TaskList;

/**
 * Handles user interaction and response formatting for CLI and GUI modes.
 */
public class Ui {
    private final Scanner scanner;
    private String responseBuffer = "";

    /**
     * Constructs a {@code Ui} instance initializing standard input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Appends a message line to standard output and the internal response buffer.
     *
     * @param message Text to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
        responseBuffer += message + "\n";
    }

    /**
     * Retrieves and clears the accumulated output string for JavaFX GUI rendering.
     *
     * @return Full text output produced during command execution.
     */
    public String getAndClearResponse() {
        String response = responseBuffer.trim();
        responseBuffer = "";
        return response;
    }

    /**
     * Reads a full line of user input from standard console input.
     *
     * @return Trimmed input string.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return "";
    }

    /**
     * Closes the underlying input scanner.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays a decorative divider line.
     */
    public void showLine() {
        showMessage("____________________________________________________________");
    }

    /**
     * Displays the welcome ASCII banner and initial greeting message.
     */
    public void showWelcome() {
        String logo = " ____       _        _        \n"
                + "|  _ \\ ___ | |_ __ _| |_ ___  \n"
                + "| |_) / _ \\| __/ _` | __/ _ \\ \n"
                + "|  __/ (_) | || (_| | || (_) |\n"
                + "|_|   \\___/ \\__\\__,_|\\__\\___/ ";
        showMessage("Hello from\n" + logo);
        showLine();
        showMessage("Hello! I'm Potato. Mr Potato.");
        showMessage("What can this Potato do for you?");
        showLine();
    }

    /**
     * Displays an error message.
     *
     * @param error Error description.
     */
    public void showError(String error) {
        showMessage(error);
    }

    /**
     * Displays formatted list of tasks contained in task list.
     *
     * @param tasks Task list collection to print.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            showMessage("There are no tasks in your list.");
            return;
        }

        try {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i));
                if (i < tasks.size() - 1) {
                    sb.append("\n");
                }
            }
            showMessage(sb.toString());
        } catch (PotatoException e) {
            showError("Error displaying task list: " + e.getMessage());
        }
    }

    /**
     * Displays task added confirmation.
     *
     * @param task Added task.
     * @param totalTasks Updated task count.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showMessage("Yessir. I've added this task:\n  " + task);
        showMessage("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays task marked done confirmation.
     *
     * @param task Marked task.
     */
    public void showTaskMarked(Task task) {
        showMessage("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Displays task unmarked confirmation.
     *
     * @param task Unmarked task.
     */
    public void showTaskUnmarked(Task task) {
        showMessage("OK, I've marked this task as not done yet:\n  " + task);
    }

    /**
     * Displays task removed confirmation for DeleteCommand.
     *
     * @param task Removed task.
     * @param totalTasks Remaining task count.
     */
    public void showTaskRemoved(Task task, int totalTasks) {
        showMessage("Good going! I've removed this task:\n  " + task);
        showMessage("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays exit message for ExitCommand.
     */
    public void showGoodbye() {
        showMessage("Byebye. Hope to see you again soon, fellow Potato!");
    }
}
