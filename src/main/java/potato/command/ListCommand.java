package potato.command;

import potato.storage.Storage;
import potato.tasklist.TaskList;
import potato.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}