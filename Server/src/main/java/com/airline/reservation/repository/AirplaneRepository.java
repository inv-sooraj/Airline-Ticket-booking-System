package com.airline.reservation.repository;

import com.airline.reservation.entity.Airplane;
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
public interface AirplaneRepository extends Repository<Airplane, Integer> {

    Airplane save(Airplane airplane);

    Collection<Airplane> findAllByStatus(byte Status);

    Optional<Airplane> findByAirplaneId(Integer airplaneId);

    
    Collection<Airplane> findAllByUserUserIdAndStatus(Integer userId, byte status);

    @Modifying
    @Transactional
    @Query("update  Airplane p set p.status = 0 where p.airplaneId in(:integers)")
    void softDeleteAllIds(List<Integer> integers);
}
