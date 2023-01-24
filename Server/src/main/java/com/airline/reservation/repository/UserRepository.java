package com.airline.reservation.repository;
import com.airline.reservation.entity.User;
import com.airline.reservation.exception.BadRequestException;

import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface UserRepository  extends Repository<User, Integer> {
    User save(User user)throws BadRequestException;
    Optional<User> findByEmail(String email);
    Optional<User> findByUserIdAndPassword(Integer userId, String password);
    boolean existsByEmail(String email);
}
