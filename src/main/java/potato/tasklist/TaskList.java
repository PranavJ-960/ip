package potato.tasklist;

import java.util.ArrayList;
import potato.exception.PotatoException;
import potato.task.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) throws PotatoException {
        if (index < 0 || index >= tasks.size()) {
            throw new PotatoException("OOPS!!! Task number " + (index + 1) + " does not exist.");
        }
        return tasks.remove(index);
    }

    public Task get(int index) throws PotatoException {
        if (index < 0 || index >= tasks.size()) {
            throw new PotatoException("OOPS!!! Task number " + (index + 1) + " does not exist.");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
}