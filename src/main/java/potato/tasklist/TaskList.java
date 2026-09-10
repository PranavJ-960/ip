package potato.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import potato.exception.PotatoException;
import potato.task.Task;

/**
 * Manages the in-memory list of tasks and supports operations to modify them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} pre-populated with an existing list of tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Filters tasks matching a keyword using Java Streams.
     *
     * @param keyword Search term to match against task descriptions.
     * @return New {@code TaskList} containing matching tasks.
     */
    public TaskList findMatchingTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        return new TaskList(new ArrayList<>(matchingTasks));
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Appends a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index Zero-based index of the task to remove.
     * @return The removed task.
     * @throws PotatoException If the index is out of bounds.
     */
    public Task remove(int index) throws PotatoException {
        if (index < 0 || index >= tasks.size()) {
            throw new PotatoException("OOPS!!! Task number " + (index + 1) + " does not exist.");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index Zero-based index of the task to retrieve.
     * @return Task at the specified index.
     * @throws PotatoException If the index is out of bounds.
     */
    public Task get(int index) throws PotatoException {
        if (index < 0 || index >= tasks.size()) {
            throw new PotatoException("OOPS!!! Task number " + (index + 1) + " does not exist.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return Total task count.
     */
    public int size() {
        return tasks.size();
    }
}