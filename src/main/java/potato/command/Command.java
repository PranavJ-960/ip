package potato.command;

import potato.exception.PotatoException;
import potato.storage.Storage;
import potato.tasklist.TaskList;
import potato.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PotatoException;

    public boolean isExit() {
        return false;
    }
}