package potato.command;

import potato.exception.PotatoException;
import potato.storage.Storage;
import potato.task.Task;
import potato.tasklist.TaskList;
import potato.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PotatoException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }
}