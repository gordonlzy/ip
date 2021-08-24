public class TodoCommand extends Command {
    protected Todo todo;
    public static final String INSTRUCTION = "todo";

    public TodoCommand(String parameter_1) throws DukeException {
        if (parameter_1.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        todo = new Todo(parameter_1);

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(this.todo);
        tasks.add(this.todo);
        ui.formatPrint("Got it. I've added this task:", "  " + this.todo.toString(), tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
