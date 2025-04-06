package command;

import dataAccessLayer.Maintenance_logDAO;
import transferObjects.maintenance_logDTO;

public class ScheduleMaintenanceCommand implements MaintenanceCommand {

    private Maintenance_logDAO dao;
    private maintenance_logDTO log;

    public ScheduleMaintenanceCommand(Maintenance_logDAO dao, maintenance_logDTO log) {
        this.dao = dao;
        this.log = log;
    }

    @Override
    public void execute() {
        dao.addMaintenanceLog(log);
    }
}


