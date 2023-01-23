/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
    
    public AirplaneListView add(AirplaneForm form);
    
    public Collection<Airplane> list();
    public AirplaneListView get(Integer airplaneId);
    public AirplaneListView update(Integer airplaneId, AirplaneForm form);
    public void deleteAllBYIds(List<Integer> integers);


}
