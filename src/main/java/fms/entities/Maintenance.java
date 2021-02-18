package fms.entities;

import java.sql.Date;
import java.util.UUID;

public class Maintenance {

    private UUID insuranceId;
    private UUID serviceId;
    private String licensePlate;
    private Date insuranceEndDate;
    private Date serviceEndDate;

    public Maintenance(UUID insuranceId, UUID serviceId, String licensePlate, Date insuranceEndDate, Date serviceEndDate) {
        this.insuranceId = insuranceId;
        this.serviceId = serviceId;
        this.licensePlate = licensePlate;
        this.insuranceEndDate = insuranceEndDate;
        this.serviceEndDate = serviceEndDate;
    }

    public UUID getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(UUID insuranceId) {
        this.insuranceId = insuranceId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public Date getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(Date serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }
}
