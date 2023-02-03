package com.airline.reservation.form;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *
 * @author lakshmimohan
 */
public class AirplaneForm {
    @NotNull
    @Size(min = 5,max = 30)
    private String airplaneName;
    @NotNull
    @Size(min = 5,max = 30)
    private String modelNo;
    @NotNull
    private Integer totalSeats;

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }
    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }
}
