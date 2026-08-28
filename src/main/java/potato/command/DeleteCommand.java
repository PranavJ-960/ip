package potato.command;

import potato.exception.PotatoException;
import potato.storage.Storage;
import potato.task.Task;
import potato.tasklist.TaskList;
import potato.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PotatoException {
        Task removed = tasks.remove(index);
        storage.save(tasks.getTasks());
        ui.showTaskRemoved(removed, tasks.size());
    }
}