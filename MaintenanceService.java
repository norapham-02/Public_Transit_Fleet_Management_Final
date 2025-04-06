package businesslayer;

import dataAccessLayer.Maintenance_logDAO;
import dataAccessLayer.Maintenance_logDAOImpl;
import java.util.List;
import transferObjects.maintenance_logDTO;

public class MaintenanceService {
    private Maintenance_logDAO dao = new Maintenance_logDAOImpl();

    public void scheduleMaintenance(maintenance_logDTO log) {
        dao.addMaintenanceLog(log);
    }

    public List<maintenance_logDTO> getUpcomingMaintenance() {
        return dao.getUpcomingMaintenance();
    }
}
