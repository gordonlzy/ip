package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.command.Command;
import duke.task.Task;

public class Duke {

    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Class constructor for Duke Class specifying the filepath
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(Paths.get(System.getProperty("user.dir"), "data", "duke.txt"));
        try {
            ArrayList<Task> taskList = storage.load();
            if (taskList.isEmpty()) {
                throw new DukeException("task list is empty");
            }
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> taskList = storage.load();
            if (taskList.isEmpty()) {
                throw new DukeException("task list is empty");
            }
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method, create an instance of Duke and run it
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Duke victor = new Duke(Paths.get(System.getProperty("user.dir"), "data", "duke.txt"));
        victor.run();
    }

    /**
     * Run the init commands of Duke
     */
    public void run() {
        storage.load();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(tasks, ui, storage);
    }
}
