package transferObjects;

public class OperatorPerformanceDTO {
    private String operatorEmail;
    private int totalLogs;
    private int outOfServiceLogs;

    public String getOperatorEmail() { return operatorEmail; }
    public void setOperatorEmail(String operatorEmail) { this.operatorEmail = operatorEmail; }

    public int getTotalLogs() { return totalLogs; }
    public void setTotalLogs(int totalLogs) { this.totalLogs = totalLogs; }

    public int getOutOfServiceLogs() { return outOfServiceLogs; }
    public void setOutOfServiceLogs(int outOfServiceLogs) { this.outOfServiceLogs = outOfServiceLogs; }
}
