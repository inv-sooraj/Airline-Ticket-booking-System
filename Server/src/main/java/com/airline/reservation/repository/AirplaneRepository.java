/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.airline.reservation.repository;

import com.airline.reservation.entity.Airplane;
import org.springframework.data.repository.Repository;

/**
 *
 * @author lakshmimohan
 */
public interface AirplaneRepository extends Repository<Airplane,Integer>  {
    Airplane save(Airplane airplane);
}
