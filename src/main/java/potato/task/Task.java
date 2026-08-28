package potato.task;

/**
 * Represents an abstract task managed by the Potato application.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the given description.
     *
     * @param description Textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon indicating whether the task is completed.
     *
     * @return {@code "X"} if done, or a single space {@code " "} if incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as incomplete.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task status and description.
     *
     * @return Formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}