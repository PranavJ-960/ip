package potato;

import potato.command.Command;
import potato.exception.PotatoException;
import potato.parser.Parser;
import potato.storage.Storage;
import potato.tasklist.TaskList;
import potato.ui.Ui;

public class Potato {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Potato(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            if (fullCommand.isEmpty()) {
                continue;
            }

            ui.showLine();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PotatoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Potato("./data/potato.txt").run();
    }
}