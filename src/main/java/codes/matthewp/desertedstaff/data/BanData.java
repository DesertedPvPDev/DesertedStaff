package codes.matthewp.desertedstaff.data;

public class BanData {

    private String uuid;
    private String reason;
    private String end;
    private String start;
    private String staff;
    private String ip;
    private String appeal;
    private String perm;

    public BanData(String uuid, String staff, String reason, String start, String end, String ip, String appeal, String perm) {
        this.uuid = uuid;
        this.reason = reason;
        this.start = start;
        this.end = end;
        this.ip = ip;
        this.appeal = appeal;
        this.perm = perm;
        this.staff = staff;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }
}
