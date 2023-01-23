/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.airline.reservation.repository;

import com.airline.reservation.entity.Airplane;
import com.airline.reservation.view.AirplaneListView;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 *
 * @author lakshmimohan
 */
public interface AirplaneRepository extends Repository<Airplane,Integer>  {
    Airplane save(Airplane airplane);
//    @Modifying
//    @Query("SELECT * FROM Airplane WHERE status = ? AND userId = ?")
    Collection<Airplane> findAllByStatusAndUserUserId(byte Status,Integer userId);
    
    Optional<Airplane> findByAirplaneIdAndUserUserId(Integer airplaneId, Integer userId);

    @Modifying
    @Transactional
    @Query("update  Airplane p set p.status = 0 where p.airplaneId in(:integers)")
    void softDeleteAllIds(List<Integer> integers);

}
