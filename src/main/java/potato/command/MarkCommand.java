package potato.command;

import potato.exception.PotatoException;
import potato.storage.Storage;
import potato.task.Task;
import potato.tasklist.TaskList;
import potato.ui.Ui;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PotatoException {
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
            ui.showTaskMarked(task);
        } else {
            task.unmarkDone();
            ui.showTaskUnmarked(task);
        }
        storage.save(tasks.getTasks());
    }
}