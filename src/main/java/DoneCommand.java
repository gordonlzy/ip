public class DoneCommand extends Command {
    protected int taskNumber;
    public static final String INSTRUCTION = "done";

    public DoneCommand(String taskNumber) throws DukeException {
        if (taskNumber.equals("")) {
            throw new DukeException("☹ OOPS!!! The task number of done cannot be empty.");
        }
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markAsDone(taskNumber - 1);
        storage.update(taskNumber, task, "m");
        ui.formatPrint("Nice! I've marked this task as done:", "  [X] " + task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
