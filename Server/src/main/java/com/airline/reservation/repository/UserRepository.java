package com.airline.reservation.repository;
import com.airline.reservation.entity.User;
import org.springframework.data.repository.Repository;

public interface UserRepository  extends Repository<User, Integer> {
    User save(User user);
}
