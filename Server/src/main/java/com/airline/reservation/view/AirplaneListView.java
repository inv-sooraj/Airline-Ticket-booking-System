package com.airline.reservation.view;

import com.airline.reservation.entity.Airplane;
import com.airline.reservation.entity.User;
import com.airline.reservation.json.Json;
import java.util.Date;

/**
 *
 * @author lakshmimohan
 */
public class AirplaneListView {

    private final Integer airplaneId;
    private final String airplaneName;
    private final String modelNo;
    private final Integer totalSeats;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;
    private final byte status;
    private User user;

    public AirplaneListView(Airplane airplane) {
        this.user = airplane.getUser();
        this.airplaneId = airplane.getAirplaneId();
        this.airplaneName = airplane.getAirplaneName();
        this.modelNo = airplane.getModelNo();
        this.totalSeats = airplane.getTotalSeats();
        this.createDate = airplane.getCreateDate();
        this.updateDate = airplane.getUpdateDate();
        this.status = airplane.getStatus();
    }

    public Integer getAirplaneId() {
        return airplaneId;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public String getModelNo() {
        return modelNo;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public byte getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

}
