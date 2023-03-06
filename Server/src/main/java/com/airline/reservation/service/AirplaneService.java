package com.airline.reservation.service;

import com.airline.reservation.entity.Airplane;
import com.airline.reservation.form.AirplaneForm;
import com.airline.reservation.view.AirplaneListView;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author lakshmimohan
 */
public interface AirplaneService {

    public AirplaneListView addPlane(AirplaneForm form);//method to add airplane

    public Collection<Airplane> listPlanes();//method to list all plane details

    public AirplaneListView getPlaneById(Integer airplaneId);//method to get airplane details of a particular id

    public AirplaneListView updatePlane(Integer airplaneId, AirplaneForm form);//method to update details of a particular plane id

    public void deletePlaneByIds(List<Integer> integers);//method to delete airplane details of listed ids

    public Collection<Airplane> getDataByUser(Integer userId);//method to get airplane details by company
}
