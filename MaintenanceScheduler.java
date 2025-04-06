package command;

public class MaintenanceScheduler {
    private MaintenanceCommand command;

    public void setCommand(MaintenanceCommand command) {
        this.command = command;
    }

    public void submit() {
        if (command != null) {
            command.execute();
        }
    }
}
