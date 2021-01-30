package fms.entities;

import java.util.UUID;
import java.sql.Date;

public class Booking {

    private UUID id;
    private String licensePlate;
    private Date startDate;
    private Date endDate;
    private String status;
    private String client;
    private String comments;

    public Booking() {

    }

    public Booking(UUID id, String licensePlate, Date startDate, Date endDate, String status,String client, String comments) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.client = client;
        this.comments = comments;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
